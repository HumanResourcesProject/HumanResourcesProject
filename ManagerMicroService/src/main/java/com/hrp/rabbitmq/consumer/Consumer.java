package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelBaseEmployee;
import com.hrp.rabbitmq.model.ModelRegisterManager;
import com.hrp.service.ManagerService;
import com.hrp.utility.StaticValues;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Consumer {
    private final ManagerService managerService;

    @RabbitListener(queues = "queue-register-manager")
    public void registerCompanyManager(ModelRegisterManager model){
        managerService.createManager(model);
    }

    @RabbitListener(queues = "queue-employee-list-for-manager")
    public void registerEmployee(List<ModelBaseEmployee> model){
        System.out.println("model icinde employee listeli olmalı o liste:  "+model.toString());
        StaticValues.modelBaseEmployees=model;
    }




}