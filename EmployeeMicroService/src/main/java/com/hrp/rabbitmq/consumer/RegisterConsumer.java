package com.hrp.rabbitmq.consumer;


import com.hrp.rabbitmq.model.ModelAuthId;
import com.hrp.rabbitmq.model.ModelSendToEmployeeMs;
import com.hrp.service.EmployeeService;
import com.hrp.utility.StaticValues;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterConsumer {
    private final EmployeeService employeeService;

    @RabbitListener(queues = "employee-register-queue")
    public void registerCompanyManager(ModelSendToEmployeeMs model){
        employeeService.createEmployee(model);
    }

    @RabbitListener(queues = "queue-auth-Id-for-employee")
    public void authId(ModelAuthId model){
        StaticValues.authId = model.getAuthId();
    }


}