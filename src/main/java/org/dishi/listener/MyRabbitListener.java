package org.dishi.listener;

import org.dishi.common.message.EmailMessage;
import org.dishi.service.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class MyRabbitListener {

    @Autowired
    private MailService mailService;

    @RabbitListener(queues = "email_queue")
    public void handle(EmailMessage message) throws MessagingException {
        mailService.sendEmail(message);
    }
}
