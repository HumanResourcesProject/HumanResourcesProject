package com.hrp.rabbitmq.producer;

import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.rabbitmq.model.ModelRegisterManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DirectProducer {

    private  final RabbitTemplate rabbitTemplate;

    public void sendRegisterAdmin(ModelRegisterAdmin model){
        System.out.println("producer ici" +model.toString());
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-register-admin",model);
    }
    public void sendRegisterManager(ModelRegisterManager model){
        System.out.println("producer ici" +model.toString());
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-register-manager",model);
    }
    public void sendRegisterEmployee(ModelRegisterEmployee model){
        System.out.println("producer ici" +model.toString());
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-register-employee",model);
    }
}
