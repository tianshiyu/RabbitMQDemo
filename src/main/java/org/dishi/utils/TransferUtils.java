package org.dishi.utils;

import org.dishi.common.message.EmailMessage;
import org.dishi.dto.EmailMessageDto;

public class TransferUtils {
    public static EmailMessage messageTransfer(EmailMessageDto dto){
        EmailMessage message = new EmailMessage();
        message.setMessage(dto.getMessage());
        message.setSubject(dto.getSubject());
        message.setTo(dto.getTo());
        return message;
    }
}
