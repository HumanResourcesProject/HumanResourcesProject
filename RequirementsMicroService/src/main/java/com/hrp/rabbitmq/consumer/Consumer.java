package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.*;
import com.hrp.service.AdvancePaymentService;
import com.hrp.service.LeaveService;
import com.hrp.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Consumer {
    private final AdvancePaymentService advancepaymentService;
    private final LeaveService leaveService;
    private final ExpenseService expenseService;
    @RabbitListener(queues = "queue-advance-payment-employee")
    public void createAdvancePayment(ModelEmployeeAdvancePaymentRequest model){
        advancepaymentService.createAdvancePayment(model);
    }
    @RabbitListener(queues = "queue-leave-employee")
    public void createLeave(ModelEmployeeLeave modelEmployeeLeave){
        System.out.println("create leave consumeeeer");
        leaveService.createLeave(modelEmployeeLeave);
    }

    @RabbitListener(queues = "queue-expense-employee")
    public void createExpense(ModelEmployeeExpense modelEmployeeExpense){
        System.out.println("create expense consumeeeer");
        expenseService.createExpense(modelEmployeeExpense);
    }



}
