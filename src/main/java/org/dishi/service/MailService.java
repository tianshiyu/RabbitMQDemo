package org.dishi.service;

import org.dishi.common.message.EmailMessage;

import javax.mail.MessagingException;

public interface MailService {
    void sendEmail(EmailMessage message) throws MessagingException;
}
