package com.brewingmadscientists.plc.model;

public class HeaterComponent extends Component {
    public HeaterComponent(PidComponent component, SwitchControl heaterSwitch) {
        super();
        setType("Heater");
        addComponent("pid", component);
        addControl("switch", heaterSwitch);
    }

    public PidComponent getPidComponent() {
        return (PidComponent)getComponent("pid");
    }

    public SwitchControl getSwitchControl() {
        return (SwitchControl) getControl("switch");
    }
}
