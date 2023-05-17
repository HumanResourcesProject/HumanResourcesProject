package com.hrp.rabbitmq.producer;

import com.hrp.rabbitmq.model.ModelTurnAllAdvancePaymentRequest;
import com.hrp.rabbitmq.model.ModelTurnAllExpenseRequest;
import com.hrp.rabbitmq.model.ModelTurnAllLeaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectProducer {
    private final RabbitTemplate rabbitTemplate;

    public void turnAllLeaveEmployee (List<ModelTurnAllLeaveRequest> model){
        rabbitTemplate.convertAndSend("exchange-direct", "binding-turnallleave-employee", model);
    }
    public void turnMyLeaveFindAll(List<ModelTurnAllLeaveRequest> model){
        System.out.println("producer ici **********");
        rabbitTemplate.convertAndSend("exchange-direct", "binding-turnmyleave-findall", model);
    }

    public void turnAllExpenseEmployee (List<ModelTurnAllExpenseRequest> model){
        rabbitTemplate.convertAndSend("exchange-direct", "binding-turnallexpense-employee", model);
    }

    public void turnAllAdvancePaymentEmployee (List<ModelTurnAllAdvancePaymentRequest> model){
        rabbitTemplate.convertAndSend("exchange-direct", "binding-turnalladvancepayment-employee", model);
    }


}
