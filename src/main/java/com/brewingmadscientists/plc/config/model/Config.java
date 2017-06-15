package com.brewingmadscientists.plc.config.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fdickey on 2017-06-09.
 */
@Component
public class Config {
    private PlcClientConfig plcClientConfig;

    @Autowired
    public Config(PlcClientConfig plcClientConfig) {
        this.plcClientConfig = plcClientConfig;
    }

    public PlcClientConfig getPlcClientConfig() {
        return plcClientConfig;
    }
}
