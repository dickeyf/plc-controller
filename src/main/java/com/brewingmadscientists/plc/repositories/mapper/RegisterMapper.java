package com.brewingmadscientists.plc.repositories.mapper;

import com.brewingmadscientists.plc.model.modbus.BitRegister;
import com.brewingmadscientists.plc.model.modbus.FloatRegister;
import com.brewingmadscientists.plc.model.modbus.IntRegister;
import com.brewingmadscientists.plc.model.modbus.Register;
import com.brewingmadscientists.plc.repositories.RegisterEntity;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapper {
    public Register map(RegisterEntity source) {
        switch (source.getType()) {
            case "BIT": {
                return new BitRegister(source.getPlcAddress(), source.getModbusAddress(), source.getNickName());
            }
            case "FLOAT": {
                return new FloatRegister(source.getPlcAddress(), source.getModbusAddress(), source.getNickName());
            }
            case "INT": {
                return new IntRegister(source.getPlcAddress(), source.getModbusAddress(), source.getNickName());
            }
        }

        return null;
    }
}