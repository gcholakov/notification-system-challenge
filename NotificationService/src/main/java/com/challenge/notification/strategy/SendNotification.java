package com.challenge.notification.strategy;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.challenge.notification.model.Notification;

public interface SendNotification {

    void send(RabbitTemplate rabbitTemplate, Notification notification);
}
