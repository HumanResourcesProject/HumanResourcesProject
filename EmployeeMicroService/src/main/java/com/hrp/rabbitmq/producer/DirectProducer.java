package com.hrp.rabbitmq.producer;

import com.hrp.rabbitmq.model.ModelBaseEmployee;
import com.hrp.rabbitmq.model.ModelEmployeeAdvancePaymentRequest;
import com.hrp.rabbitmq.model.ModelEmployeeExpense;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@EnableRabbit
public class DirectProducer {

    private  final RabbitTemplate rabbitTemplate;

    public void sendAdvanceEmployee(ModelEmployeeAdvancePaymentRequest model){
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-advance-payment-employee",model);
    }
    public void sendLeaveEmployee(ModelEmployeeLeave model){
        System.out.println("leave employee produceri");
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-leave-employee",model);
    }

    public void sendExpenseEmployee(ModelEmployeeExpense model) {
        System.out.println("Expense employee produceri");
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-expense-employee",model);
    }

    public void sendEmployeeListForManager(List<ModelBaseEmployee> modelEmployess) {
        System.out.println("0000000000000");
        System.out.println("emp list producer dönüsü"+modelEmployess.stream().toList());
        System.out.println("38");
        rabbitTemplate.convertAndSend("exchange-direct","binding-key-employee-list-for-manager",modelEmployess);
    }
}
