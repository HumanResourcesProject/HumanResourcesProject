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
    private String companyManagerEmailKey = "company-manager-email-key";
    private String adminRegisterKey = "admin-register-key" ;
    private String companyManagerRegisterAuthKey = "company-manager-register-auth-key" ;
    private String companyManagerSendToKey = "company-manager-send-to-key" ;


    // Queue
    private  String adminEmailQueue = "admin-email-queue";
    private  String companyManagerEmailQueue = "company-manager-email-queue";
    private  String adminRegisterQueue = "admin-register-queue";
    private  String companyManagerRegisterAuthQueue = "company-manager-register-auth-queue";
    private  String companyManagerSendToQueue = "company-manager-send-to-queue";


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
    Queue companyManagerEmailQueue(){
        return new Queue(companyManagerEmailQueue);
    }
    @Bean
    Queue adminRegisterQueue(){
        return new Queue(adminRegisterQueue);
    }
    @Bean
    Queue companyManagerRegisterAuthQueue(){
        return new Queue(companyManagerRegisterAuthQueue);
    }
    @Bean
    Queue companyManagerSendToQueue(){
        return new Queue(companyManagerSendToQueue);
    }

    /**
     * ---- Binding ----
     */
    @Bean
    public Binding bindingAdminMail(final Queue adminEmailQueue ,final DirectExchange directExchange ){
        return BindingBuilder.bind(adminEmailQueue).to(directExchange).with(adminEmailKey);
    }
    @Bean
    public Binding bindingCompanyManagerMail(final Queue companyManagerEmailQueue ,final DirectExchange directExchange ){
        return BindingBuilder.bind(companyManagerEmailQueue).to(directExchange).with(companyManagerEmailKey);
    }
    @Bean
    public Binding bindingAdminRegister(final Queue adminRegisterQueue ,final DirectExchange directExchange){
        return BindingBuilder.bind(adminRegisterQueue).to(directExchange).with(adminRegisterKey);
    }
    @Bean
    public Binding bindingCompanyManagerRegisterAuth(final Queue companyManagerRegisterAuthQueue ,final DirectExchange directExchange){
        return BindingBuilder.bind(companyManagerRegisterAuthQueue).to(directExchange).with(companyManagerRegisterAuthKey);
    }
    @Bean
    public Binding bindingCompanyManagerSendTo(final Queue companyManagerSendToQueue ,final DirectExchange directExchange){
        return BindingBuilder.bind(companyManagerSendToQueue).to(directExchange).with(companyManagerSendToKey);
    }
}