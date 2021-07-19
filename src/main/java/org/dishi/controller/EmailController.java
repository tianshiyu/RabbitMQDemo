package org.dishi.controller;

import org.dishi.dto.EmailMessageDto;
import org.dishi.service.RabbitService;
import org.dishi.utils.TransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private RabbitService rabbitService;

    @PostMapping("mail")
    private void sendEmail(@RequestBody EmailMessageDto message){
        rabbitService.sendMessage(TransferUtils.messageTransfer(message));
    }

    @PostMapping("ttlMail")
    private void sendTtlEmail(@RequestBody EmailMessageDto message){
        rabbitService.sendTtlMessage(TransferUtils.messageTransfer(message), message.getDelayTimes());
    }
}
