package com.challenge.notification.service;

import com.challenge.notification.model.Notification;

public interface NotifierService {

    void receiveMessageForSMS(Notification notification);
    void receiveMessageForEmail(Notification notification);
    void receiveMessageForSlack(Notification notification);
}
