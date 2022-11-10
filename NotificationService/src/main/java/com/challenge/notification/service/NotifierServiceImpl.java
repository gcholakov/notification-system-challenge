package com.challenge.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.challenge.notification.model.Notification;

@Service
public class NotifierServiceImpl implements NotifierService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    @Override
    public void receiveMessage(Notification notification) {
        log.info("Received message: " + notification);
    }
}
