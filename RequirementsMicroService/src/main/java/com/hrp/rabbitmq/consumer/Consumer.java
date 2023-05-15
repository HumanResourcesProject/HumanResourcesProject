package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelEmployeePermissionRequest;
import com.hrp.service.AdvancepaymentService;
import com.hrp.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Consumer {
    private final AdvancepaymentService service;
    @RabbitListener(queues = "queue-advance-employee")
    public void createPermission(ModelEmployeePermissionRequest model){
        System.out.println(model.toString());
        service.createAdvancePayment(model);
    }



}
