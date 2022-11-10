package com.challenge.notification.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.challenge.notification.model.Notification;
import com.challenge.notification.strategy.SendEmailNotification;
import com.challenge.notification.strategy.SendNotification;
import com.challenge.notification.strategy.SendSMSNotification;
import com.challenge.notification.strategy.SendSlackNotification;

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

        SendNotification sendNotification;

        switch (notification.getChannelType()) {
            case SMS:
                sendNotification = new SendSMSNotification(exchange, routingKeySMS);
                break;
            case Email:
                sendNotification = new SendEmailNotification(exchange, routingKeyEmail);
                break;
            case Slack:
                sendNotification = new SendSlackNotification(exchange, routingKeySlack);
                break;

            default: throw new IllegalArgumentException("Channel not implemented!");
        }

        sendNotification.send(rabbitTemplate, notification);
    }
}
