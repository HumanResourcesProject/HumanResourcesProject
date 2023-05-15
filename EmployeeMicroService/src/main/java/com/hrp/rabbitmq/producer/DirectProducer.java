package com.hrp.rabbitmq.producer;

import com.hrp.rabbitmq.model.ModelEmployeeAdvancePaymentRequest;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@EnableRabbit
public class DirectProducer {

    private  final RabbitTemplate rabbitTemplate;

    public void sendRegisterEmployee(ModelRegisterEmployee model){
        System.out.println("producer ici" +model.toString());
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-register-employee",model);
    }

    public void sendAdvanceEmployee(ModelEmployeeAdvancePaymentRequest model){
        System.out.println("producer ici" +model.toString());
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-advance-employee",model);
    }
}
