package com.brewingmadscientists.plc.config.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by fdickey on 2017-06-09.
 */
@Component
@ConfigurationProperties(prefix="plc")
public class PlcClientConfig {
    private String address;
    private int port;

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
