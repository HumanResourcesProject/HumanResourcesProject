package com.hrp.rabbitmq.producer;

import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.rabbitmq.model.ModelRegisterCompanyManager;
import com.hrp.rabbitmq.model.ModelSendToCompanyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerDirectService {
    private String exchangeDirect = "exchange-direct";
    private String adminRegisterKey = "admin-register-key" ;
    private String companyManagerRegisterAuthKey = "company-manager-register-auth-key" ;
    private String companyManagerSendToKey = "company-manager-send-to-key" ;
    private final RabbitTemplate rabbitTemplate;

    public void sendRegisterAdmin(ModelRegisterAdmin model){
        rabbitTemplate.convertAndSend(exchangeDirect,adminRegisterKey,model);
    }
    public void sendToAuthRegisterCompanyManager(ModelRegisterCompanyManager model){
        rabbitTemplate.convertAndSend(exchangeDirect,companyManagerRegisterAuthKey,model);
    }
    public void sendToCompanyManager(ModelSendToCompanyManager model){
        rabbitTemplate.convertAndSend(exchangeDirect,companyManagerSendToKey,model);
    }
}
