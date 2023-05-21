package com.hrp.config.rabbitmq;

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
    @Value("${rabbitmq.key.requirements.payment}")
    private String bindingKeyAdvancePaymentEmployee;
    @Value("${rabbitmq.key.requirements.leave}")
    private String bindingKeyLeaveEmployee;
    @Value("${rabbitmq.key.requirements.expense}")
    private String bindingKeyExpenseEmployee;
    @Value("${rabbitmq.key.list.employee}")
    private String bindingKeyEmployeeListForManager;


    // Queue
    @Value("${rabbitmq.queue.register.admin}")
    private  String queueRegisterAdmin ;
    @Value("${rabbitmq.queue.register.manager}")
    private  String queueRegisterManager ;
    @Value("${rabbitmq.queue.register.employee}")
    private  String queueRegisterEmployee;
    @Value("${rabbitmq.queue.requirements.payment}")
    private  String queueAdvancePaymentEmployee;
    @Value("${rabbitmq.queue.requirements.leave}")
    private  String queueLeaveEmployee;
    @Value("${rabbitmq.queue.requirements.expense}")
    private  String queueExpenseEmployee ;
    @Value("${rabbitmq.queue.list.employee}")
    private String queueEmployeeListForManager;

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
    @Bean
    Queue queueExpenseEmployee(){
        return new Queue(queueExpenseEmployee);
    }

    @Bean
    Queue queueEmployeeListForManager(){
        return new Queue(queueEmployeeListForManager);
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

    @Bean
    public Binding bindingExpenseEmployee(final Queue queueExpenseEmployee,final DirectExchange directExchange){
        return BindingBuilder.bind(queueExpenseEmployee).to(directExchange).with(bindingKeyExpenseEmployee);
    }
    @Bean
    public Binding bindingEmployeeListForManager(final Queue queueEmployeeListForManager,final DirectExchange directExchange){
        return BindingBuilder.bind(queueEmployeeListForManager).to(directExchange).with(bindingKeyEmployeeListForManager);
    }

}
