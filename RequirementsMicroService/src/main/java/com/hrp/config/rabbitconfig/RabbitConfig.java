package com.hrp.config.rabbitconfig;

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
    private String bindingKeyTurnAllLeaveEmployee= "binding-turnallleave-employee" ;
    private String bindingKeyTurnMyLeaveFindAll= "binding-turnmyleave-findall" ;
    private String bindingKeyTurnAllExpenseEmployee= "binding-turnallexpense-employee" ;
    private String bindingKeyTurnAllAdvancePaymentEmployee= "binding-turnalladvancepayment-employee" ;




    // Queue
    private  String queueRegisterAdmin = "queue-register-admin";
    private  String queueRegisterManager = "queue-register-manager";
    private  String queueRegisterEmployee= "queue-register-employee";
    private String queueTurnAllLeaveEmployee= "queue-turnallleave-employee" ;
    private String queueTurnMyLeaveFindAll= "queue-turnmyleave-findall" ;
    private String queueTurnAllExpenseEmployee= "queue-turnallexpense-employee" ;

    private String queueTurnAllAdvancePaymentEmployee= "queue-turnalladvancepayment-employee" ;





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
    Queue queueTurnAllLeaveEmployee(){
        return new Queue(queueTurnAllLeaveEmployee);
    }

    @Bean
    Queue queueTurnMyLeaveFindAll(){
        return new Queue(queueTurnMyLeaveFindAll);
    }
    @Bean
    Queue queueTurnAllExpenseEmployee(){
        return new Queue(queueTurnAllExpenseEmployee);
    }
    @Bean
    Queue queueTurnAllAdvancePaymentEmployee(){
        return new Queue(queueTurnAllAdvancePaymentEmployee);
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
    public Binding bindingTurnAllLeaveEmployee(final Queue queueTurnAllLeaveEmployee,final DirectExchange directExchange){
        return BindingBuilder.bind(queueTurnAllLeaveEmployee).to(directExchange).with(bindingKeyTurnAllLeaveEmployee);
    }
    @Bean
    public Binding bindingTurnMyLeavesFindAll(final Queue queueTurnMyLeaveFindAll,final DirectExchange directExchange){
        return BindingBuilder.bind(queueTurnMyLeaveFindAll).to(directExchange).with(bindingKeyTurnMyLeaveFindAll);
    }
    @Bean
    public Binding bindingTurnAllExpenseEmployee(final Queue queueTurnAllExpenseEmployee,final DirectExchange directExchange){
        return BindingBuilder.bind(queueTurnAllExpenseEmployee).to(directExchange).with(bindingKeyTurnAllExpenseEmployee);
    }
    @Bean
    public Binding bindingKeyTurnAllAdvancePaymentEmployee(final Queue queueTurnAllAdvancePaymentEmployee,final DirectExchange directExchange){
        return BindingBuilder.bind(queueTurnAllAdvancePaymentEmployee).to(directExchange).with(bindingKeyTurnAllAdvancePaymentEmployee);
    }


}
