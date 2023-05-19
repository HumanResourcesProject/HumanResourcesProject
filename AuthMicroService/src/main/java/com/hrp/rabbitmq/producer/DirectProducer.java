package com.hrp.rabbitmq.producer;

import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.rabbitmq.model.ModelRegisterManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@EnableRabbit
public class DirectProducer {

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange.direct}")
    String exchange;

    @Value("${rabbitmq.key.register.admin}")
    private String keyRegisterAdmin;

    public void sendRegisterAdmin(ModelRegisterAdmin model){
            rabbitTemplate.convertAndSend(exchange,keyRegisterAdmin,model);
    }
    public void sendRegisterManager(ModelRegisterManager model){
        rabbitTemplate.convertAndSend(exchange,"binding-key-register-manager",model);
    }
    public void sendRegisterEmployee(ModelRegisterEmployee model){
            rabbitTemplate.convertAndSend(exchange,"binding-key-register-employee",model);
    }
}
