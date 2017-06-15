package com.brewingmadscientists.plc.config.service;

import com.brewingmadscientists.plc.config.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fdickey on 2017-06-12.
 */
@Service
public class ConfigService {

    private Config appConfig;

    @Autowired
    public ConfigService(Config appConfig) {
        this.appConfig = appConfig;
    }

    public Config getAppConfig() {
        return appConfig;
    }
}
