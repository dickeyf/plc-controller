package com.brewingmadscientists.plc.services;

import com.brewingmadscientists.plc.model.*;
import com.brewingmadscientists.plc.model.modbus.BitRegister;
import com.brewingmadscientists.plc.model.modbus.FloatRegister;
import com.brewingmadscientists.plc.model.modbus.IntRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ComponentService {
    private Map<String, Probe> probes = new HashMap<>();
    private Map<String, Component> components = new HashMap<>();
    private Map<String, Control> controls = new HashMap<>();
    private RegisterService registerService;

    @Autowired
    public ComponentService(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostConstruct
    public void init() {
        //Create the Water Thermometer component
        TemperatureProbe waterTempUncalProbe =
                new TemperatureProbe((FloatRegister)registerService.getRegister("DF1"));
        TemperatureProbe waterTempCalProbe =
                new TemperatureProbe((FloatRegister)registerService.getRegister("DF100"));
        ParamControl waterTempCalK =
                new ParamControl((FloatRegister)registerService.getRegister("DF120"));
        ParamControl waterTempCalC =
                new ParamControl((FloatRegister)registerService.getRegister("DF110"));
        ThermometerComponent thermometerComponent = new ThermometerComponent(
                waterTempUncalProbe,
                waterTempCalProbe,
                waterTempCalK,
                waterTempCalC);
        addProbe("waterTempCalProbe", waterTempCalProbe);
        addProbe("waterTempUncalProbe", waterTempUncalProbe);
        addControl("waterTempCalK", waterTempCalK);
        addControl("waterTempCalC", waterTempCalC);
        addComponent("boilerThermometer", thermometerComponent);

        //Create the PID Component
        ParamControl kp = new ParamControl((FloatRegister)registerService.getRegister("DF14"));
        ParamControl ki = new ParamControl((FloatRegister)registerService.getRegister("DF15"));
        ParamControl kd = new ParamControl((FloatRegister)registerService.getRegister("DF16"));
        ParamControl targetTemp = new ParamControl((FloatRegister)registerService.getRegister("DF13"));
        PwmProbe pwmProbe = new PwmProbe((IntRegister)registerService.getRegister("DS2"));
        PidComponent pidComponent = new PidComponent(
                thermometerComponent,
                kp,
                ki,
                kd,
                targetTemp,
                pwmProbe);
        addControl("kp", kp);
        addControl("ki", ki);
        addControl("kd", kd);
        addControl("targetTemp", targetTemp);
        addProbe("pwmProbe", pwmProbe);
        addComponent("headerOnePidController", pidComponent);

        //Create the Heater Component
        SwitchControl heaterSwitch = new SwitchControl((BitRegister)registerService.getRegister("C23"));
        HeaterComponent heaterComponent = new HeaterComponent(pidComponent, heaterSwitch);
        addControl("heaterSwitch", heaterSwitch);
        addComponent("heaterOne", heaterComponent);

        //Add the Pump Component
        SwitchControl pumpSwitch = new SwitchControl((BitRegister)registerService.getRegister("C6"));
        PumpComponent pumpComponent = new PumpComponent(pumpSwitch);
        addControl("pumpSwitch", pumpSwitch);
        addComponent("pumpOne", pumpComponent);
    }

    public void addProbe(String name, Probe probe) {
        probe.setName(name);
        probes.put(probe.getName(), probe);
    }

    public void addControl(String name, Control control) {
        control.setName(name);
        controls.put(control.getName(), control);
    }

    public void addComponent(String name, Component component) {
        component.setName(name);
        components.put(component.getName(), component);
    }

    public List<Probe> getProbes() {
        return new LinkedList<>(probes.values());
    }

    public List<Control> getControls() {
        return new LinkedList<>(controls.values());
    }

    public List<Component> getComponents() {
        return new LinkedList<>(components.values());
    }

    public Component getComponent(String id) {
        return components.get(id);
    }

    public Control getControl(String id) {
        return controls.get(id);
    }
}
