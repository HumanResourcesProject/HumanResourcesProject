package com.hrp.rabbitmq.producer;

import com.hrp.rabbitmq.model.ModelBaseRequirmentFindAll;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendfindAllLeave(ModelBaseRequirmentFindAll model){
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-findallleave-employee",model);
    }

}
