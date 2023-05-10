package com.hrp.rabbitmq.consumer;

import com.hrp.mapper.IAuthMapper;
import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterAdminConsumer {

    private final AuthService authService;

     @RabbitListener(queues = "register-queue")
    public void registerAdmin(ModelRegisterAdmin model){
         authService.registerAdmin(model);
     }
}
