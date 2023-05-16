package com.hrp.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // exchange
    private String exchangeDirect = "exchange-direct";
    private String exchangeFanout = "exchange-fanout";
    private String exchangeTopic = "exchange-topic";


    // Key

    private String bindingKeyRegisterAdmin= "binding-key-register-admin" ;
    private String bindingKeyRegisterManager= "binding-key-register-manager" ;
    private String bindingKeyRegisterEmployee= "binding-key-register-employee" ;
    private String bindingKeyAdvancePaymentEmployee = "binding-key-advance-payment-employee" ;
    private String bindingKeyLeaveEmployee = "binding-key-leave-employee" ;






    // Queue
    private  String queueRegisterAdmin = "queue-register-admin";
    private  String queueRegisterManager = "queue-register-manager";
    private  String queueRegisterEmployee= "queue-register-employee";
    private  String queueAdvancePaymentEmployee = "queue-advance-payment-employee";

    private  String queueLeaveEmployee = "queue-leave-employee";






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

    @Bean
    Queue queueAdvancePaymentEmployee(){
        return new Queue(queueAdvancePaymentEmployee);
    }

    @Bean
    Queue queueLeaveEmployee(){
        return new Queue(queueLeaveEmployee);
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

    @Bean
    public Binding bindingAdvancePaymentEmployee(final Queue queueAdvancePaymentEmployee,final DirectExchange directExchange){
        return BindingBuilder.bind(queueAdvancePaymentEmployee).to(directExchange).with(bindingKeyAdvancePaymentEmployee);
    }
    @Bean
    public Binding bindingLeaveEmployee(final Queue queueLeaveEmployee,final DirectExchange directExchange){
        return BindingBuilder.bind(queueLeaveEmployee).to(directExchange).with(bindingKeyLeaveEmployee);
    }

}
