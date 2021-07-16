package org.dishi.service;

import org.dishi.common.message.EmailMessage;

public interface RabbitService {
    void sendMessage(EmailMessage message);
}
