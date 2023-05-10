package com.hrp.rabbitmq.producer;

import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerDirectService {
    private String exchangeDirect = "exchange-direct";
    private String registerKey = "register-key";
    private final RabbitTemplate rabbitTemplate;

    public void sendRegisterAdmin(ModelRegisterAdmin model){
        rabbitTemplate.convertAndSend(exchangeDirect,registerKey,model);
    }
}
