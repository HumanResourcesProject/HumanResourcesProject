package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.repository.entity.Employee;
import com.hrp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Consumer {

    private final EmployeeService employeeService;
    @RabbitListener(queues = "queue-register-employee")
    public void registerEmployee(ModelRegisterEmployee model){
        System.out.println("employee consumer");
    employeeService.createEmployee(model);
    }
}
