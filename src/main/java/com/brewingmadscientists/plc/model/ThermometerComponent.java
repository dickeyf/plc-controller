package com.brewingmadscientists.plc.model;

public class ThermometerComponent extends Component {
    public ThermometerComponent(
            TemperatureProbe uncalibrated,
            TemperatureProbe calibrated,
            ParamControl k,
            ParamControl c) {
        super();
        setType("Thermometer");
        addControl("k", k);
        addControl("c", c);
        addProbe("uncalibrated", uncalibrated);
        addProbe("calibrated", calibrated);
    }

    public ParamControl getKParamControl() {
        return (ParamControl)getControl("k");
    }

    public ParamControl getCParamControl() {
        return (ParamControl)getControl("c");
    }

    public TemperatureProbe getUncalibratedTemperatureProbe() {
        return (TemperatureProbe)getProbe("uncalibrated");
    }

    public TemperatureProbe getCalibratedTemperatureProbe() {
        return (TemperatureProbe)getProbe("calibrated");
    }

    public float getCalibratedTemperature() {
        return getCalibratedTemperatureProbe().getTemperature();
    }

    public float getUncalibratedTemperature() {
        return getUncalibratedTemperatureProbe().getTemperature();
    }

    void setCalibrationParams(float k, float c) {
        getKParamControl().setValue(k);
        getCParamControl().setValue(c);
    }
}
