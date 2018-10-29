package com.brewingmadscientists.plc.model;

import com.brewingmadscientists.plc.model.modbus.BitRegister;

public class SwitchControl extends Control {
    public enum State {
        OFF,
        ON
    }

    public SwitchControl(BitRegister register) {
        super();
        this.setType("Switch");
        this.setRegister(register);
    }

    public BitRegister getRegister() {
        return (BitRegister)super.getRegister();
    }

    public State getState() {
        return getRegister().getValue().booleanValue() ? State.ON:State.OFF;
    }

    public void turnOn() {
        getRegister().setValue("true");
    }

    public void turnOff() {
        getRegister().setValue("false");
    }

    public void setState(State targetState) {
        getRegister().setValue( (targetState==State.ON) ? "true" : "false");
    }
}
