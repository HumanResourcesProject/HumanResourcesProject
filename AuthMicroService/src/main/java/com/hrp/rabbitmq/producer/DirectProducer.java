package com.hrp.rabbitmq.producer;

import com.hrp.rabbitmq.model.ModelAuthId;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DirectProducer {
    private  final RabbitTemplate rabbitTemplate;

    public void sendAuthIdForEmployee(ModelAuthId model){
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-auth-Id-for-employee",model);
    }
    public void sendAuthIdForAdmin(ModelAuthId model){
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-auth-Id-for-admin",model);
    }

}
