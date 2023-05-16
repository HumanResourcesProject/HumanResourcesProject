package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelBaseRequirmentFindAll;
import com.hrp.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindConsumers {
    private final LeaveService leaveService;


    @RabbitListener(queues = "queue-findallleave-employee")
    public void findAllLeave(ModelBaseRequirmentFindAll modelBaseRequirmentFindAll){
        System.out.println("findallleave consumeeeer");
        leaveService.findAllLeaveRequestByCompany(modelBaseRequirmentFindAll);
    }

}
