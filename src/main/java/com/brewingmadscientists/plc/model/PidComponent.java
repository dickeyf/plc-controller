package com.brewingmadscientists.plc.model;

public class PidComponent extends Component {
    public PidComponent(
            ThermometerComponent thermometerComponent,
            ParamControl kp,
            ParamControl ki,
            ParamControl kd,
            ParamControl targetTemp,
            PwmProbe pwmProbe) {
        super();
        setType("Pid");
        addComponent("thermometer", thermometerComponent);
        addControl("kp", kp);
        addControl("ki", ki);
        addControl("kd", kd);
        addControl("targetTemp", targetTemp);
        addProbe("pwm", pwmProbe);
    }

    public ThermometerComponent getThermometer() {
        return (ThermometerComponent)getComponent("thermometer");
    }

    public ParamControl getKpParam() {
        return (ParamControl)getControl("kp");
    }

    public ParamControl getKiParam() {
        return (ParamControl)getControl("ki");
    }

    public ParamControl getKdParam() {
        return (ParamControl)getControl("kd");
    }

    public ParamControl getTargetTempParam() {
        return (ParamControl)getControl("targetTemp");
    }

    public PwmProbe getPwmProbe() {
        return (PwmProbe)getProbe("pwm");
    }
}
