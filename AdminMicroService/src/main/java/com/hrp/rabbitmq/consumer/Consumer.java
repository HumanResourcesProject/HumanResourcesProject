package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Consumer {
    private final AdminService adminService;

    @RabbitListener(queues = "queue-register-admin")
    public void registerAdmin(ModelRegisterAdmin model){
        adminService.adminRegister(model);
    }

}
