package com.hrp.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    // exchange
    private String exchangeDirect = "exchange-direct";
    private String exchangeFanout = "exchange-fanout";
    private String exchangeTopic = "exchange-topic";


    // Key

    private String bindingKeyAuthIdForEmployee = "binding-key-auth-Id-for-employee" ;
    private String bindingKeyAuthIdForAdmin= "binding-key-auth-Id-for-admin" ;




    // Queue
    private  String queueAuthIdForAdmin = "queue-auth-Id-for-admin";
    private  String queueAuthIdForEmployee = "queue-auth-Id-for-employee";





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
    Queue queueAuthIdForAdmin(){
        return new Queue(queueAuthIdForAdmin);
    }

    @Bean
    Queue queueAuthIdForEmployee(){
        return new Queue(queueAuthIdForEmployee);
    }



    /**
     * ---- Binding ----
     */
    @Bean
    public Binding bindingAuthIdForEmployee(final Queue queueAuthIdForEmployee,final DirectExchange directExchange){
        return BindingBuilder.bind(queueAuthIdForEmployee).to(directExchange).with(bindingKeyAuthIdForEmployee);
    }

    @Bean
    public Binding bindingAuthIdForAdmin(final Queue queueAuthIdForAdmin,final DirectExchange directExchange){
        return BindingBuilder.bind(queueAuthIdForAdmin).to(directExchange).with(bindingKeyAuthIdForAdmin);
    }


}
