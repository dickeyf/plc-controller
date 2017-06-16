package com.brewingmadscientists.plc.services;

import com.brewingmadscientists.plc.config.model.PlcClientConfig;
import com.digitalpetri.modbus.master.ModbusTcpMaster;
import com.digitalpetri.modbus.master.ModbusTcpMasterConfig;
import com.digitalpetri.modbus.requests.ReadCoilsRequest;
import com.digitalpetri.modbus.requests.ReadHoldingRegistersRequest;
import com.digitalpetri.modbus.requests.WriteMultipleRegistersRequest;
import com.digitalpetri.modbus.requests.WriteSingleCoilRequest;
import com.digitalpetri.modbus.responses.ReadCoilsResponse;
import com.digitalpetri.modbus.responses.ReadHoldingRegistersResponse;
import com.digitalpetri.modbus.responses.WriteMultipleRegistersResponse;
import com.digitalpetri.modbus.responses.WriteSingleCoilResponse;
import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

/**
 * Created by fdickey on 2017-06-12.
 */
@Service
public class PlcService {
    private PlcClientConfig plcClientConfig;
    private ModbusTcpMasterConfig tcpMasterConfig;
    private ModbusTcpMaster master = null;

    private static final Log logger = LogFactory.getLog(PlcService.class);

    @Autowired
    public PlcService(PlcClientConfig plcClientConfig) {
        this.plcClientConfig = plcClientConfig;

        tcpMasterConfig = new ModbusTcpMasterConfig.Builder(plcClientConfig.getAddress()).setPort(plcClientConfig.getPort()).build();
    }

    @PostConstruct
    public void Initialize() {
        master = new ModbusTcpMaster(tcpMasterConfig);

        CompletableFuture<ModbusTcpMaster> future = master.connect();
        future.join();
        logger.info("Connected to " + tcpMasterConfig.getAddress() + ":" + tcpMasterConfig.getPort());
    }

    public void writeFloat(int modBusAddress, float value) {
        int bits = Float.floatToIntBits(value);

        byte[] array = new byte[4];
        array[1] = (byte)(bits & 0xff);
        array[0] = (byte)((bits >> 8) & 0xff);
        array[3] = (byte)((bits >> 16) & 0xff);
        array[2] = (byte)((bits >> 24) & 0xff);

        CompletableFuture<WriteMultipleRegistersResponse> future =
                master.sendRequest(new WriteMultipleRegistersRequest(modBusAddress, 2, array), 1);

        WriteMultipleRegistersResponse response = future.join();
    }

    public float readFloat(int modBusAddress) {
        CompletableFuture<ReadHoldingRegistersResponse> future =
                master.sendRequest(new ReadHoldingRegistersRequest(modBusAddress, 2), 1);

        ReadHoldingRegistersResponse response = future.join();

        ByteBuf buffer = response.getRegisters();
        byte[] array = new byte[4];
        buffer.readBytes(array, 0, 4);

        //Swap both 16-bits registers to correct endianness
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putShort(buffer.getShort(2));
        byteBuffer.putShort(buffer.getShort(0));
        ReferenceCountUtil.release(response);

        return byteBuffer.getFloat(0);
    }

    public boolean readBit(int modBusAddress) {
        CompletableFuture<ReadCoilsResponse> future =
                master.sendRequest(new ReadCoilsRequest(modBusAddress, 1), 1);
        ReadCoilsResponse response = future.join();
        return response.getCoilStatus().getBoolean(0);
    }

    public void writeBit(int modBusAddress, boolean value) {
        CompletableFuture<WriteSingleCoilResponse> future =
                master.sendRequest(new WriteSingleCoilRequest(modBusAddress, value), 1);
        future.join();
    }
}
