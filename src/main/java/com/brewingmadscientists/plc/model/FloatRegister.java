package com.brewingmadscientists.plc.model;

/**
 * Created by fdickey on 2017-06-13.
 */
public class FloatRegister extends Register<Float> {

    public FloatRegister() {
        super();
    }

    public FloatRegister(long id, String description, int modbusAddress) {
        super(id, description, modbusAddress);
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
