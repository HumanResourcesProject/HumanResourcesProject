package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.rabbitmq.model.ModelRegisterCompanyManager;
import com.hrp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterConsumer {

    private final AuthService authService;

    @RabbitListener(queues = "admin-register-queue")
    public void registerAdmin(ModelRegisterAdmin model){
         authService.registerAdmin(model);
     }
    @RabbitListener(queues = "company-manager-register-auth-queue")
    public void registerAdmin(ModelRegisterCompanyManager model){
        authService.registerCompanyManager(model);
    }
}
