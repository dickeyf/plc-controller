package com.brewingmadscientists.plc.model.modbus;

/**
 * Created by fdickey on 2017-06-15.
 */
public class BitRegister extends Register<Boolean> {

    public BitRegister() {
        super();
    }

    public BitRegister(String plcAddress, String modbusAddress, String nickname)
    {
        super(plcAddress, modbusAddress, nickname);
    }

    public Class<Boolean> getClazz() {
        return Boolean.class;
    }

    @Override
    public Boolean getValue() {
        return getPlcService().readBit(getModbusAddress());
    }

    @Override
    public void setValue(String value) {
        getPlcService().writeBit(getModbusAddress(), Boolean.parseBoolean(value));
    }
}
