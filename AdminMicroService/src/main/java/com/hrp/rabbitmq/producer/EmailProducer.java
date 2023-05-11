package com.hrp.rabbitmq.producer;


import com.hrp.rabbitmq.model.EmailAdminModel;
import com.hrp.rabbitmq.model.EmailCompanyManagerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailProducer {

    private String exchangeDirect = "exchange-direct" ;
    private String adminEmailKey = "admin-email-key";
    private String companyManagerEmailKey = "company-manager-email-key";


    private  final RabbitTemplate rabbitTemplate;

    public void sendAdminMail(EmailAdminModel model){
        rabbitTemplate.convertAndSend(exchangeDirect,adminEmailKey,model);
    }
    public void sendCompanyManagerMail(EmailCompanyManagerModel model){
        rabbitTemplate.convertAndSend(exchangeDirect,companyManagerEmailKey,model);
    }
}
