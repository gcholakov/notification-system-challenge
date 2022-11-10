package com.challenge.notification.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.challenge.notification.model.Notification;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public NotificationServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(Notification notification) {
        rabbitTemplate.convertAndSend(exchange, routingkey, notification);
    }
}
