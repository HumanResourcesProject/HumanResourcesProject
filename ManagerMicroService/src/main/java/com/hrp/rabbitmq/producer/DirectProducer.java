package com.hrp.rabbitmq.producer;

import com.hrp.dto.response.EmployeeRequestAndResponseDto;
import com.hrp.rabbitmq.model.ModelBaseEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectProducer {

    private final RabbitTemplate rabbitTemplate;
    public void sendFindAllMyEmployee(ModelBaseEmployee model){
        System.out.println("prodcuer ici find all employeesi");
        rabbitTemplate.convertAndSend("exchange-direct","binging-key-find-all-my-employee",model);
    }

}
