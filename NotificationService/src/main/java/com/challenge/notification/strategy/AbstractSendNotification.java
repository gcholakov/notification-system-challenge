package com.challenge.notification.strategy;

public abstract class AbstractSendNotification implements SendNotification {

    protected String exchange;
    protected String routingKey;
}
