package com.brewingmadscientists.plc.model;

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
@Entity
@Table(name = "plc_registers")
public abstract class Register<T> {
    @Autowired
    @Transient
    private PlcService plcService;

    @Id
    private long id;
    private String description;
    private int modbusAddress;

    Register() {
        setId(0);
        setDescription(null);
        setModbusAddress(0);
    }

    Register(long id, String description, int modbusAddress) {
        this.setId(id);
        this.setDescription(description);
        this.setModbusAddress(modbusAddress);
    }

    public String toString() {
        return String.format("%s (%s @ %x)", description, this.getClass().getSimpleName(), modbusAddress);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
