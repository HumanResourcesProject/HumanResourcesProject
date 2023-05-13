package com.hrp.config.rabbitmq;

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
    private String adminEmailKey = "admin-email-key";
    private String bindingKeyRegisterAdmin= "binding-key-register-admin" ;
    private String bindingKeyRegisterManager= "binding-key-register-manager" ;
    private String bindingKeyRegisterEmployee= "binding-key-register-employee" ;




    // Queue
    private  String adminEmailQueue = "admin-email-queue";
    private  String queueRegisterAdmin = "queue-register-admin";
    private  String queueRegisterManager = "queue-register-manager";
    private  String queueRegisterEmployee= "queue-register-employee";



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
    Queue adminEmailQueue(){
        return new Queue(adminEmailQueue);
    }
    @Bean
    Queue queueRegisterAdmin(){
        return new Queue(queueRegisterAdmin);
    }

    @Bean
    Queue queueRegisterManager(){
        return new Queue(queueRegisterManager);
    }

    @Bean
    Queue queueRegisterEmployee(){
        return new Queue(queueRegisterEmployee);
    }


    /**
     * ---- Binding ----
     */
    @Bean
    public Binding bindingAdminMail(final Queue adminEmailQueue ,final DirectExchange directExchange ){
        return BindingBuilder.bind(adminEmailQueue).to(directExchange).with(adminEmailKey);
    }
    @Bean
    public Binding bindingRegisterAdmin(final Queue queueRegisterAdmin,final DirectExchange directExchange){
        return BindingBuilder.bind(queueRegisterAdmin).to(directExchange).with(bindingKeyRegisterAdmin);
    }
    @Bean
    public Binding bindingRegisterManager(final Queue queueRegisterManager,final DirectExchange directExchange){
        return BindingBuilder.bind(queueRegisterManager).to(directExchange).with(bindingKeyRegisterManager);
    }
    @Bean
    public Binding bindingRegisterEmployee(final Queue queueRegisterEmployee,final DirectExchange directExchange){
        return BindingBuilder.bind(queueRegisterEmployee).to(directExchange).with(bindingKeyRegisterEmployee);
    }




}