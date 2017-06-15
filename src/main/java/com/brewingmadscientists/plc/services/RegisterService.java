package com.brewingmadscientists.plc.services;

import com.brewingmadscientists.plc.model.FloatRegister;
import com.brewingmadscientists.plc.model.Register;
import com.brewingmadscientists.plc.repositories.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by fdickey on 2017-06-13.
 */
@Service
public class RegisterService {
    private RegisterRepository registerRepository;
    private AutowireCapableBeanFactory beanFactory;
    private HashMap<Long, Register> registers = new HashMap<>();
    private long nextId = 1;

    @Autowired
    public RegisterService(AutowireCapableBeanFactory beanFactory, RegisterRepository registerRepository) {
        this.beanFactory = beanFactory;
        this.registerRepository = registerRepository;

        createFloatRegister("Fermenter Temperature", 0x7000);
        createFloatRegister("Fermenter Target Temperature", 0x70C6);
        createFloatRegister("Fermenter Coolant Dip Temperature", 0x70C8);
        createFloatRegister("Fermenter Coolant Chiller Cutoff", 0x70CA);
        createFloatRegister("Fermenter Coolant Pump Start Temperature", 0x718E);
        createFloatRegister("Fermenter Coolant Pump Stop Temperature", 0x7190);
    }

    public void createFloatRegister(String description, int modbusAddress) {
        Register register = new FloatRegister(nextId, description, modbusAddress);
        beanFactory.autowireBean(register);
        registers.put(nextId, register);
        nextId ++;
    }

    public HashMap<Long, Register> getRegisters() {
        return registers;
    }

    public Register getRegister(long sensorId) {
        return registers.get(sensorId);
    }
}
