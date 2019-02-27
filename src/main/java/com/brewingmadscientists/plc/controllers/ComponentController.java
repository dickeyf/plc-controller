package com.brewingmadscientists.plc.controllers;

import com.brewingmadscientists.plc.model.Component;
import com.brewingmadscientists.plc.model.Control;
import com.brewingmadscientists.plc.model.ParamControl;
import com.brewingmadscientists.plc.model.SwitchControl;
import com.brewingmadscientists.plc.services.ComponentService;
import com.brewingmadscientists.plc.services.PlcService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComponentController {
    @Autowired
    ComponentService componentService;

    private static final Log logger = LogFactory.getLog(PlcService.class);

    @RequestMapping(value = "/api/v0/plc/components", method = RequestMethod.GET)
    @ResponseBody
    public List<Component> getComponents() {
        return componentService.getComponents();
    }

    @RequestMapping(value = "/api/v0/plc/components/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Component getComponent(@PathVariable("id") String id) {
        return componentService.getComponent(id);
    }

    @RequestMapping(value = "/api/v0/plc/controls/{id}/value", method = RequestMethod.PUT)
    @ResponseBody
    public Control setControlValue(@PathVariable("id") String id, @RequestBody String value) {
        Control control = componentService.getControl(id);
        if (control instanceof SwitchControl) {
            SwitchControl switchControl = (SwitchControl) control;
            switchControl.setState(value.equals("ON") ? SwitchControl.State.ON : SwitchControl.State.OFF);
        } else {
            ParamControl paramControl = (ParamControl) control;
            paramControl.setValue(Float.valueOf(value));
        }
        return control;
    }
}
