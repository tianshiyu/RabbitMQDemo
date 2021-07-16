package org.dishi.dto;

import lombok.Getter;

@Getter
public enum QueueEnum {
    /**
     * 邮件通知队列
     */
    QUEUE_EMAIL("email_exchange", "email_queue", "email"),
    /**
     * 邮件通知ttl队列（延迟发送）
     */
    QUEUE_TTL_EMAIL("email_exchange_ttl", "email_queue_ttl", "email_ttl");

    /**
     * 交换名称
     */
    private final String exchange;
    /**
     * 队列名称
     */
    private final String name;
    /**
     * 路由键
     */
    private final String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
