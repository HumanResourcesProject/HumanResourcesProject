package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelBaseRequirmentFindAll;
import com.hrp.repository.entity.AdvancedPayment;
import com.hrp.service.AdvancepaymentService;
import com.hrp.service.LeaveService;
import com.hrp.service.SpendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindConsumers {
    private final LeaveService leaveService;

    private final SpendingService spendingService;
    private final AdvancepaymentService advancepaymentService;


    @RabbitListener(queues = "queue-findallleave-employee")
    public void findAllLeave(ModelBaseRequirmentFindAll modelBaseRequirmentFindAll){
        System.out.println("findallleave consumeeeer");
        leaveService.findAllLeaveRequestByCompany(modelBaseRequirmentFindAll);
    }

    @RabbitListener(queues = "queue-findallexpense-employee")
    public void findAllExpense(ModelBaseRequirmentFindAll modelBaseRequirmentFindAll){
        System.out.println("findallspending consumeeeer");
        spendingService.findAllExpenseRequestByCompany(modelBaseRequirmentFindAll);
    }
    @RabbitListener(queues = "queue-findalladvancepayment-employee")
    public void findAllAdvancePayment(ModelBaseRequirmentFindAll modelBaseRequirmentFindAll){
        System.out.println("findalladvancepayment consumeeeer");
        advancepaymentService.findAllAdvancePaymentRequestByCompany(modelBaseRequirmentFindAll);
    }


}
