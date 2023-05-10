package com.hrp.rabbitmq.producer;


import com.hrp.rabbitmq.model.EmailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailProducer {

    private String exchangeDirect = "exchange-direct" ;

    private String emailBindingKey = "email-key";

    private  final RabbitTemplate rabbitTemplate;

    public void sendActivationCode(EmailModel model){
        rabbitTemplate.convertAndSend(exchangeDirect,emailBindingKey,model);
    }
}
