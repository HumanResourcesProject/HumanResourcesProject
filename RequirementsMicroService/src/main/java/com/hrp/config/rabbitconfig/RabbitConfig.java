package com.hrp.config.rabbitconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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

    @Value("${rabbitmq.key.register.admin}")
    private String bindingKeyRegisterAdmin ;
    @Value("${rabbitmq.key.register.manager}")
    private String bindingKeyRegisterManager;
    @Value("${rabbitmq.key.register.employee}")
    private String bindingKeyRegisterEmployee ;


    // Queue
    @Value("${rabbitmq.queue.register.admin}")
    private  String queueRegisterAdmin ;
    @Value("${rabbitmq.queue.register.manager}")
    private  String queueRegisterManager ;
    @Value("${rabbitmq.queue.register.employee}")
    private  String queueRegisterEmployee;



    /**
     * ---- Exchange ----
     */

    @Bean
    DirectExchange exchangeDirect() {
        return new DirectExchange(exchangeDirect);
    }


    /**
     * ---- Queue ----
     */
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
