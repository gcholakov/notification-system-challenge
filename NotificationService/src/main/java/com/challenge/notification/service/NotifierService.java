package com.challenge.notification.service;

import com.challenge.notification.model.Notification;

public interface NotifierService {

    void receiveMessage(Notification notification);
}
