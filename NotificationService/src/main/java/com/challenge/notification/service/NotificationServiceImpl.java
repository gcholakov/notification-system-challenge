package com.challenge.notification.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.challenge.notification.model.ChannelType;
import com.challenge.notification.model.Notification;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey.sms}")
    private String routingKeySMS;
    @Value("${spring.rabbitmq.routingkey.email}")
    private String routingKeyEmail;
    @Value("${spring.rabbitmq.routingkey.slack}")
    private String routingKeySlack;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public NotificationServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(Notification notification) {
        switch (notification.getChannelType()) {
            case SMS:
                rabbitTemplate.convertAndSend(exchange, routingKeySMS, notification);
                break;
            case Email:
                rabbitTemplate.convertAndSend(exchange, routingKeyEmail, notification);
                break;
            case Slack:
                rabbitTemplate.convertAndSend(exchange, routingKeySlack, notification);
                break;
        }
    }
}
