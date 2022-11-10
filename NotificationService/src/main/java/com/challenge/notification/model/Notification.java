package com.challenge.notification.model;

import lombok.Data;

@Data
public class Notification {
    private ChannelType channelType;
    private String content;
}
