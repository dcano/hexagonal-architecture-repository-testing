package com.twba;

import com.twba.kernel.fwk.ValueObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CustomNotificationChannel extends ValueObject {

    private final List<NotificationChannelType> notificationChannels;

    private CustomNotificationChannel(List<NotificationChannelType> notificationChannels) {
        this.notificationChannels = Collections.unmodifiableList(notificationChannels);
    }

    static CustomNotificationChannel with(List<NotificationChannelType> notificationChannelTypes) {
        return new CustomNotificationChannel(notificationChannelTypes);
    }

    static CustomNotificationChannel empty() {
        return new CustomNotificationChannel(new ArrayList<>());
    }

    public List<NotificationChannelType> getNotificationChannels() {
        return notificationChannels;
    }
}
