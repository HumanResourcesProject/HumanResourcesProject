package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.EmailAdminModel;
import com.hrp.rabbitmq.model.EmailCompanyManagerModel;
import com.hrp.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailConsumer {

    private final MailSenderService mailSenderService;

    @RabbitListener(queues ="admin-email-queue")
    public void adminSendMail(EmailAdminModel model){
        log.info("Model {} ",model.toString());
        mailSenderService.sendAdminMail(model);
    }
    @RabbitListener(queues ="company-manager-email-queue")
    public void companyManagerSendMail(EmailCompanyManagerModel model){
        log.info("Model {} ",model.toString());
        mailSenderService.sendCompanyMail(model);
    }
}
