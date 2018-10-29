package com.brewingmadscientists.plc.model;

import com.brewingmadscientists.plc.model.modbus.IntRegister;

public class PwmProbe extends Probe {
    public PwmProbe(IntRegister register) {
        super();
        setType("PwmProbe");
        setRegister(register);
    }

    public IntRegister getRegister() {
        return (IntRegister)super.getRegister();
    }

    public int getPwmDutycycle() {
        return getRegister().getValue().intValue();
    }
}
