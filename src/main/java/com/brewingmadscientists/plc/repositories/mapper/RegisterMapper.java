package com.brewingmadscientists.plc.repositories.mapper;

import com.brewingmadscientists.plc.model.BitRegister;
import com.brewingmadscientists.plc.model.FloatRegister;
import com.brewingmadscientists.plc.model.Register;
import com.brewingmadscientists.plc.repositories.RegisterEntity;

public class RegisterMapper {
    Register map(RegisterEntity source) {
        Register register = null;

        switch (source.getType()) {
            case "Boolean": {
                register = new BitRegister(source.getId(), source.getDescription(), source.getModbusAddress());
            }
            case "Float": {
                register = new FloatRegister(source.getId(), source.getDescription(), source.getModbusAddress());
            }
        }

        return register;
    }

    RegisterEntity map(Register register) {
        RegisterEntity registerEntity = new RegisterEntity();
        registerEntity.setId(register.getId());
        registerEntity.setDescription(register.getDescription());
        registerEntity.setModbusAddress(register.getModbusAddress());
        registerEntity.setType(register.getClazz().getSimpleName());
        return registerEntity;
    }
}