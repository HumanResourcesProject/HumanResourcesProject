package com.hrp.rabbitmq.consumer;

import com.hrp.rabbitmq.model.EmailModel;
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

    @RabbitListener(queues ="email-queue")
    public void activationCode(EmailModel model){
        log.info("Model {} ",model.toString());
        mailSenderService.sendMail(model);
    }

}
