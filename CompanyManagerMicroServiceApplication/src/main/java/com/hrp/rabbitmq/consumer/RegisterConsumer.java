package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.ModelAuthId;
import com.hrp.rabbitmq.model.ModelSendToCompanyManager;
import com.hrp.service.CompanyManagerService;
import com.hrp.utility.StaticValues;
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

  // auth ıd for company manager
     @RabbitListener(queues = "queue-auth-Id-for-employee")
     public void authId(ModelAuthId model){
         StaticValues.authId = model.getAuthId();
      }


}