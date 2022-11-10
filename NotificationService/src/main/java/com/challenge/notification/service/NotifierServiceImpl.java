package com.challenge.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.challenge.notification.model.Notification;

@Service
public class NotifierServiceImpl implements NotifierService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "${spring.rabbitmq.queue.email}", concurrency = "${notifier.email.concurrency}")
    @Override
    public void receiveMessageForEmail(Notification notification) {

        log.info("Received message for Email: " + notification);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue.sms}")
    @Override
    public void receiveMessageForSMS(Notification notification) {

        log.info("Received message for SMS: " + notification);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue.slack}")
    @Override
    public void receiveMessageForSlack(Notification notification) {

        log.info("Received message for Slack: " + notification);
    }
}
