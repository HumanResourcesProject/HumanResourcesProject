package com.hrp.rabbitmq.producer;

import com.hrp.rabbitmq.model.ModelEmployeeAdvancePaymentRequest;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@EnableRabbit
public class DirectProducer {

    private  final RabbitTemplate rabbitTemplate;

    public void sendAdvanceEmployee(ModelEmployeeAdvancePaymentRequest model){
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-advance-payment-employee",model);
    }
    public void sendLeaveEmployee(ModelEmployeeLeave model){
        System.out.println("leave employee produceri");
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-leave-employee",model);
    }
}
