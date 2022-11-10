package com.challenge.notification.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents notification message.
 */
@Getter
@Setter
@ToString
public class Notification {

    private ChannelType channelType;
    private String content;
}
