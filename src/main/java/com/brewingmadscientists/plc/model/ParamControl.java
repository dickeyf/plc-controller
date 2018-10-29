package com.brewingmadscientists.plc.model;

import com.brewingmadscientists.plc.model.modbus.FloatRegister;

public class ParamControl extends Control {
    public ParamControl(FloatRegister register) {
        super();
        this.setType("Parameter");
        this.setRegister(register);
    }

    public FloatRegister getRegister() {
        return (FloatRegister)super.getRegister();
    }

    public float getValue() {
        return getRegister().getValue().floatValue();
    }

    public void setValue(float value) {
        getRegister().setValue(Float.toString(value));
    }
}
