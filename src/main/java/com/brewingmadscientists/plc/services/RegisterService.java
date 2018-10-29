package com.brewingmadscientists.plc.services;

import com.brewingmadscientists.plc.model.modbus.Register;
import com.brewingmadscientists.plc.repositories.RegisterEntity;
import com.brewingmadscientists.plc.repositories.RegisterRepository;
import com.brewingmadscientists.plc.repositories.mapper.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fdickey on 2017-06-13.
 */
@Service
public class RegisterService {
    private AutowireCapableBeanFactory beanFactory;
    private HashMap<String, Register> registers = new HashMap<>();
    private RegisterRepository registerRepository;
    private RegisterMapper registerMapper;

    @Autowired
    public RegisterService(AutowireCapableBeanFactory beanFactory, RegisterRepository registerRepository, RegisterMapper registerMapper) {
        this.beanFactory = beanFactory;
        this.registerRepository = registerRepository;
        this.registerMapper = registerMapper;
    }

    @PostConstruct
    public void init() {
        List<RegisterEntity> registers = registerRepository.findAll();
        for (RegisterEntity registerEntity : registers) {
            addRegister(registerEntity);
        }
    }

    public void createRegister(RegisterEntity registerEntity) {
        registerRepository.save(registerEntity);
        addRegister(registerEntity);
    }

    public void addRegister(RegisterEntity registerEntity) {
        Register register = registerMapper.map(registerEntity);
        beanFactory.autowireBean(register);
        addRegister(register);
    }

    public void addRegister(Register register) {
        registers.put(register.getPlcAddress(), register);
    }

    public HashMap<String, Register> getRegisters() {
        return registers;
    }

    public Register getRegister(String registerId) {
        return registers.get(registerId);
    }
}
