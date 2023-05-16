package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelEmployeeAdvancePaymentRequest;
import com.hrp.rabbitmq.model.ModelEmployeeExpense;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import com.hrp.service.AdvancepaymentService;
import com.hrp.service.LeaveService;
import com.hrp.service.SpendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Consumer {
    private final AdvancepaymentService advancepaymentService;
    private final LeaveService leaveService;

    private final SpendingService spendingService;
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
        spendingService.createExpense(modelEmployeeExpense);
    }
    @RabbitListener(queues = "queue-my-leave")
    public void myLeaveFindAll ( ModelEmployeeLeave model){
        System.out.println("my leave consumeeeer");
        leaveService.myLeaveFindAll(model);
    }





}
