package com.brewingmadscientists.plc.model;

import com.brewingmadscientists.plc.model.modbus.FloatRegister;

public class TemperatureProbe extends Probe {
    public TemperatureProbe(FloatRegister register) {
        super();
        this.setType("Temperature");
        this.setRegister(register);
    }

    public FloatRegister getRegister() {
        return (FloatRegister) super.getRegister();
    }

    float getTemperature() {
        return getRegister().getValue().floatValue();
    }
}
