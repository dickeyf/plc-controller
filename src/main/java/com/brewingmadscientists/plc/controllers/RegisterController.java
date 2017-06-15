package com.brewingmadscientists.plc.controllers;

import com.brewingmadscientists.plc.model.Register;
import com.brewingmadscientists.plc.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by fdickey on 2017-06-13.
 */
@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @RequestMapping("/api/v0/registers")
    @ResponseBody
    Collection<Register> registers() {
        return registerService.getRegisters().values();
    }

    @RequestMapping("/api/v0/registers/{id}")
    @ResponseBody
    Register getRegister(@PathVariable("id") long id) {
        return registerService.getRegister(id);
    }

    @RequestMapping("/api/v0/registers/{id}/value")
    @ResponseBody
    Object getRegisterValue(@PathVariable("id") long id) {
        return registerService.getRegister(id).getValue();
    }

    @RequestMapping(value = "/api/v0/registers/{id}/value",method = RequestMethod.POST, consumes = "text/plain")
    @ResponseBody
    void setRegisterValue(@PathVariable("id") long id, @RequestBody String value) {
        registerService.getRegister(id).setValue(value);
    }
}
