package com.challenge.notification.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.challenge.notification.model.Notification;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotifierServiceImpl implements NotifierService {

    @RabbitListener(queues = "${spring.rabbitmq.queue.email}", concurrency = "2")
    @Override
    public void receiveMessageForEmail(Notification notification) {

        log.info("Received message for sending to e-mail: " + notification);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue.sms}")
    @Override
    public void receiveMessageForSMS(Notification notification) {

        log.info("Received message for sending via SMS: " + notification);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue.slack}")
    @Override
    public void receiveMessageForSlack(Notification notification) {

        log.info("Received message for pushing to Slack: " + notification);
    }
}
