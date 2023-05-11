package com.hrp.service;

import com.hrp.rabbitmq.model.EmailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender javaMailSender;

    public void sendMail(EmailModel model){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("${MAILUSERNAME}");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("Aktivasyon kodunuz: ");
        mailMessage.setText(model.getActivationCode());
        javaMailSender.send(mailMessage);
        System.out.println("YOLLANDI");
    }}
