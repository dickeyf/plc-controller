package com.brewingmadscientists.plc.controllers;

import com.brewingmadscientists.plc.model.modbus.Register;
import com.brewingmadscientists.plc.repositories.RegisterEntity;
import com.brewingmadscientists.plc.services.PlcService;
import com.brewingmadscientists.plc.services.RegisterService;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * Created by fdickey on 2017-06-13.
 */
@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    private static final Log logger = LogFactory.getLog(PlcService.class);

    @RequestMapping(value = "/api/v0/plc/registers", method = RequestMethod.GET)
    @ResponseBody
    Collection<Register> registers() {
        return registerService.getRegisters().values();
    }

    @RequestMapping("/api/v0/plc/registers/{id}")
    @ResponseBody
    Register getRegister(@PathVariable("id") String id) {
        return registerService.getRegister(id);
    }

    @RequestMapping("/api/v0/plc/registers/{id}/value")
    @ResponseBody
    Object getRegisterValue(@PathVariable("id") String id) {
        return registerService.getRegister(id).getValue();
    }

    @RequestMapping(value = "/api/v0/plc/registers/{id}/value",method = RequestMethod.POST, consumes = "text/plain")
    @ResponseBody
    void setRegisterValue(@PathVariable("id") String id, @RequestBody String value) {
        registerService.getRegister(id).setValue(value);
    }

    @PostMapping("/api/v0/plc/registers")
    @ResponseBody
    void processRegisterList(@RequestParam("data") MultipartFile file) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            MappingIterator<RegisterEntity> readValues =
            mapper.readerFor(RegisterEntity.class).with(bootstrapSchema).readValues(file.getBytes());
            List<RegisterEntity> registers = readValues.readAll();

            for (RegisterEntity register : registers) {
                registerService.createRegister(register);
            }
        } catch (Exception e) {
            logger.error("Error occurred while loading object list", e);
        }
    }
}
