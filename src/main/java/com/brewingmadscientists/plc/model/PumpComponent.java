package com.brewingmadscientists.plc.model;

public class PumpComponent extends Component{
    public PumpComponent(SwitchControl switchControl) {
        super();
        setType("Pump");
        addControl("switch", switchControl);
    }

    public SwitchControl getSwitchControl() {
        return (SwitchControl) getControl("switch");
    }

    public void turnOn() {
        getSwitchControl().turnOn();
    }

    public void turnOff() {
        getSwitchControl().turnOff();
    }

    public void setState(SwitchControl.State targetState) {
        getSwitchControl().setState(targetState);
    }

    public SwitchControl.State getState() {
        return getSwitchControl().getState();
    }

    public boolean isOn() {
        return getState() == SwitchControl.State.ON;
    }

    public boolean isOff() {
        return getState() == SwitchControl.State.OFF;
    }
}
