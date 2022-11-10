package com.challenge.notification.model;

public enum ChannelType {

    SMS("SMS"), Email("Email"), Slack("Slack");

    private final String type;

    ChannelType(String type) {
        this.type = type;
    }

    public String getType() {

        return type;
    }
}
