package com.hrp.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // exchange
    @Value("${rabbitmq.exchange.direct}")
    private String exchangeDirect ;
    @Value("${rabbitmq.exchange.fanout}")
    private String exchangeFanout ;
    @Value("${rabbitmq.exchange.topic}")
    private String exchangeTopic ;


    // Key
    @Value("${rabbitmq.key.mail.admin}")
    private String adminEmailKey = "admin-email-key";
    @Value("${rabbitmq.key.register.admin}")
    private String bindingKeyRegisterAdmin ;
    @Value("${rabbitmq.key.register.manager}")
    private String bindingKeyRegisterManager;
    @Value("${rabbitmq.key.register.employee}")
    private String bindingKeyRegisterEmployee ;
    @Value("${rabbitmq.key.list.leave}")
    private String bindingKeyFindAllLeaveEmployee;


    // Queue
    @Value("${rabbitmq.queue.mail.admin}")
    private  String adminEmailQueue = "admin-email-queue";
    @Value("${rabbitmq.queue.register.admin}")
    private  String queueRegisterAdmin ;
    @Value("${rabbitmq.queue.register.manager}")
    private  String queueRegisterManager ;
    @Value("${rabbitmq.queue.register.employee}")
    private  String queueRegisterEmployee;
    @Value("${rabbitmq.queue.list.leave}")
    private  String queueFindAllLeaveEmployee;


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

    @Bean
    Queue queueFindAllLeaveEmployee(){
        return new Queue(queueFindAllLeaveEmployee);
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

    @Bean
    public Binding bindingFindAllLeaveEmployee(final Queue queueFindAllLeaveEmployee,final DirectExchange directExchange){
        return BindingBuilder.bind(queueFindAllLeaveEmployee).to(directExchange).with(bindingKeyFindAllLeaveEmployee);
    }


}