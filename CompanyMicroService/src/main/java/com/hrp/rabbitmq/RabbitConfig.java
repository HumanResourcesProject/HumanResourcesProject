package com.hrp.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // exchange
    private String exchangeDirect = "exchange-direct";
    private String exchangeFanout = "exchange-fanout";
    private String exchangeTopic = "exchange-topic";


    // Key
    private String emailKey = "email-key";
    private String registerKey = "register-key" ;


    // Queue
    private  String queueEmail = "email-queue";
    private  String queueRegister = "register-queue";


    /**
     * ---- Exchange ----
     */

    @Bean
    DirectExchange exchangeDirect() {
        return new DirectExchange(exchangeDirect);
    }

    @Bean
    FanoutExchange exchangeFanout() {
        return new FanoutExchange(exchangeFanout);
    }

    @Bean
    TopicExchange exchangeTopic() {
        return new TopicExchange(exchangeTopic);
    }


    /**
     * ---- Queue ----
     */

    @Bean
    Queue emailQueue(){
        return new Queue(queueEmail);
    }
    @Bean
    Queue registerQueue(){
        return new Queue(queueRegister);
    }

    /**
     * ---- Binding ----
     */
    @Bean
    public Binding bindingMail(final Queue emailQueue , final DirectExchange exchangeAuth ){
        return BindingBuilder.bind(emailQueue).to(exchangeAuth).with(emailKey);
    }
    @Bean
    public Binding bindingRegister(final Queue registerQueue ,final DirectExchange exchangeAuth){
        return BindingBuilder.bind(registerQueue).to(exchangeAuth).with(registerKey);
    }
}