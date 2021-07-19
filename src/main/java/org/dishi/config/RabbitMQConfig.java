package org.dishi.config;

import org.dishi.dto.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**
     * 邮件实时消费队列所绑定的交换机
     */
    @Bean
    DirectExchange emailDirect() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_EMAIL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 邮件延迟消费队列所绑定的交换机
     */
    @Bean
    DirectExchange emailTtlDirect() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_EMAIL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 邮件实时消费队列
     */
    @Bean
    public Queue emailQueue() {
        return new Queue(QueueEnum.QUEUE_EMAIL.getName());
    }

    /**
     * 邮件延迟消费队列
     */
    @Bean
    public Queue emailTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_EMAIL.getName())
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_EMAIL.getExchange())//到期后转发的交换机
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_EMAIL.getRouteKey())//到期后转发的路由键
                .build();
    }

    /**
     * 将邮件队列绑定到交换机
     */
    @Bean
    Binding emailBinding(DirectExchange emailDirect,Queue emailQueue){
        return BindingBuilder
                .bind(emailQueue)
                .to(emailDirect)
                .with(QueueEnum.QUEUE_EMAIL.getRouteKey());
    }

    /**
     * 将延迟邮件队列绑定到交换机
     */
    @Bean
    Binding emailTtlBinding(DirectExchange emailTtlDirect,Queue emailTtlQueue){
        return BindingBuilder
                .bind(emailTtlQueue)
                .to(emailTtlDirect)
                .with(QueueEnum.QUEUE_TTL_EMAIL.getRouteKey());
    }

    /**
     * 设置可以发送JavaBean对象，由Spring Boot自动序列化为JSON并以文本消息传递
     */
    @Bean
    MessageConverter createMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
