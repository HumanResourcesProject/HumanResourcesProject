package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.service.EmployeeService;
import com.hrp.utility.StaticValues;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class Consumer {

    private final EmployeeService employeeService;
    @RabbitListener(queues = "queue-register-employee")
    public void registerEmployee(ModelRegisterEmployee model){
        System.out.println("HHHHHHHHHHHHHHHH");
            employeeService.createEmployee(model);
    }


}
