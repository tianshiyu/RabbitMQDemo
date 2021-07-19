package org.dishi.service.impl;

import lombok.AllArgsConstructor;
import org.dishi.common.message.EmailMessage;
import org.dishi.dto.QueueEnum;
import org.dishi.service.RabbitService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RabbitServiceImpl implements RabbitService {

    private final AmqpTemplate rabbitTemplate;

    @Override
    public void sendMessage(EmailMessage message) {
        sendTtlMessage(message, 0);
    }

    @Override
    public void sendTtlMessage(EmailMessage message, final long delayTimes) {
        rabbitTemplate.convertAndSend(QueueEnum.QUEUE_TTL_EMAIL.getExchange(), QueueEnum.QUEUE_TTL_EMAIL.getRouteKey(), message,
        new MessagePostProcessor(){
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //给消息设置延迟毫秒值
                message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                return message;
            }
        });
    }
}
