package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelSendToCompanyManager;
import com.hrp.service.CompanyManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterConsumer {
    private final CompanyManagerService companyManagerService;

    @RabbitListener(queues = "company-manager-send-to-queue")
    public void registerCompanyManager(ModelSendToCompanyManager model){
        companyManagerService.createCompanyManager(model);
    }

}
