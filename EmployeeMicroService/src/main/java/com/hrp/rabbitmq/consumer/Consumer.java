package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelBaseEmployee;
import com.hrp.rabbitmq.model.ModelLeaveCount;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.service.EmployeeService;
import com.hrp.utility.StaticValues;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class Consumer {

    private final EmployeeService employeeService;
    @Value("${rabbitmq.queues.leave.count}")
    private String queueLeaveCount;
    @RabbitListener(queues = "queue-register-employee")
    public void registerEmployee(ModelRegisterEmployee model){
        System.out.println("HHHHHHHHHHHHHHHH");
            employeeService.createEmployee(model);
    }
    @RabbitListener(queues = "queue-find-all-my-employee")
    public void findAllMyEmployee(ModelBaseEmployee model){
        System.out.println("findall consumer 24");
        employeeService.findAllMyEmployeeForManager(model);
    }
    @RabbitListener(queues = "queue-leave-count")
    public void leaveCount(ModelLeaveCount model){
        employeeService.updateLeaveCount(model);
    }


}
