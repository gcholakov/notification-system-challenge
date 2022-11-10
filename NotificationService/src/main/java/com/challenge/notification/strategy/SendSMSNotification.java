package com.challenge.notification.strategy;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.challenge.notification.model.Notification;

public class SendSMSNotification extends AbstractSendNotification {

    public SendSMSNotification(String exchange, String routingKey) {

        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    @Override
    public void send(RabbitTemplate rabbitTemplate, Notification notification) {

        rabbitTemplate.convertAndSend(exchange, routingKey, notification);
    }
}
