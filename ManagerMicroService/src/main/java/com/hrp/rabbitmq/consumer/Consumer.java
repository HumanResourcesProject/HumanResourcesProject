package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelRegisterManager;
import com.hrp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Consumer {
    private final ManagerService managerService;

    @RabbitListener(queues = "queue-register-manager")
    public void registerCompanyManager(ModelRegisterManager model){
        managerService.createManager(model);
    }




}