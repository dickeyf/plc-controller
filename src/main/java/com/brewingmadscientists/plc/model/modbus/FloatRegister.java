package com.brewingmadscientists.plc.model.modbus;

/**
 * Created by fdickey on 2017-06-13.
 */
public class FloatRegister extends Register<Float> {

    public FloatRegister() {
        super();
    }

    public FloatRegister(String plcAddress, String modbusAddress, String nickname) {
        super(plcAddress, modbusAddress, nickname);
    }

    public Class<Float> getClazz() {
        return Float.class;
    }

    public Float getValue() {
        return getPlcService().readFloat(getModbusAddress());
    }

    @Override
    public void setValue(String value) {
        getPlcService().writeFloat(getModbusAddress(), Float.parseFloat(value));
    }
}
