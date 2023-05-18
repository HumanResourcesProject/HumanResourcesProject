package com.hrp.rabbitmq.producer;

import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.rabbitmq.model.ModelRegisterManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@EnableRabbit
public class DirectProducer {

    private  final RabbitTemplate rabbitTemplate;

    public void sendRegisterAdmin(ModelRegisterAdmin model){
        System.out.println("producer ici" +model.toString());
        try {
            rabbitTemplate.convertAndSend("exchange-direct","binding-key-register-admin",model);
            System.out.println("girdi alt satıra");

        } catch (Exception e){

        }
    }
    public void sendRegisterManager(ModelRegisterManager model){
        System.out.println("producer ici" +model.toString());
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-register-manager",model);
    }
    public void sendRegisterEmployee(ModelRegisterEmployee model){
            rabbitTemplate.convertAndSend("exchange-direct","binding-key-register-employee",model);
    }
}
