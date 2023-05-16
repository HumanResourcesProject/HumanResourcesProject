package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelEmployeeAdvancePaymentRequest;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import com.hrp.service.AdvancepaymentService;
import com.hrp.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Consumer {
    private final AdvancepaymentService advancepaymentService;
    private final LeaveService leaveService;
    @RabbitListener(queues = "queue-advance-payment-employee")
    public void createAdvancePayment(ModelEmployeeAdvancePaymentRequest model){
    try {
        System.out.println("advance payment consumer ici");
        advancepaymentService.createAdvancePayment(model);
        System.out.println("*******");

    } catch (Exception e) {
        System.out.println("hata aldik ------------------\n\n----------------\n\n");
        e.printStackTrace();
        e.getMessage();
        System.out.println("\n\n\n hata aldik ------------------------\n\n----------------");

    }
    }
    @RabbitListener(queues = "queue-leave-employee")
    public void createLeave(ModelEmployeeLeave modelEmployeeLeave){
        System.out.println("create leave consumeeeer");
        leaveService.createLeave(modelEmployeeLeave);
    }



}
