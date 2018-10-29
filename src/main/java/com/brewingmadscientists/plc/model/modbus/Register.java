package com.brewingmadscientists.plc.model.modbus;

import com.brewingmadscientists.plc.services.PlcService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by fdickey on 2017-06-15.
 */
public abstract class Register<T> {
    @Autowired
    private PlcService plcService;

    private String plcAddress;
    private int modbusAddress;
    private String nickname;

    Register() {
        setModbusAddress(0);
    }

    Register(String plcAddress, String modbusAddress, String nickname) {
        String hexAddress = modbusAddress.subSequence(0, modbusAddress.length()-1).toString();
        this.plcAddress = plcAddress;
        this.setModbusAddress(Integer.parseInt(hexAddress, 16));
        this.nickname = nickname;
    }

    public String toString() {
        return String.format("%s @ %x", this.getClass().getSimpleName(), modbusAddress);
    }

    public int getModbusAddress() {
        return modbusAddress;
    }

    public void setModbusAddress(int modbusAddress) {
        this.modbusAddress = modbusAddress;
    }

    protected PlcService getPlcService() {
        return plcService;
    }

    @JsonIgnore
    abstract public Class<T> getClazz();

    @JsonIgnore
    abstract public T getValue();
    abstract public void setValue(String value);

    public String getPlcAddress() {
        return plcAddress;
    }

    public void setPlcAddress(String plcAddress) {
        this.plcAddress = plcAddress;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
