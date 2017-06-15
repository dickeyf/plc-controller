package com.brewingmadscientists.plc.config.controllers;

import com.brewingmadscientists.plc.config.model.Config;
import com.brewingmadscientists.plc.config.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @RequestMapping("/config")
    @ResponseBody
    Config config() {
        return configService.getAppConfig();
    }
}
