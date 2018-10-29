package com.brewingmadscientists.plc.model.modbus;

public class IntRegister extends Register<Integer> {
    public IntRegister() {
        super();
    }

    public IntRegister(String plcAddress, String modbusAddress, String nickname) {
        super(plcAddress, modbusAddress, nickname);
    }

    public Class<Integer> getClazz() {
        return Integer.class;
    }

    public Integer getValue() {
        return getPlcService().readInt(getModbusAddress());
    }

    @Override
    public void setValue(String value) {
        getPlcService().writeInt(getModbusAddress(), Integer.parseInt(value));
    }
}
