package com.twba.students;

import java.util.Arrays;

enum NotificationChannelType {
    EMAIL,
    SMS,
    WHATSAPP;

    static boolean contains(String notificationChannel) {
        return Arrays.stream(values()).anyMatch(nct -> nct.toString().equalsIgnoreCase(notificationChannel));
    }
}
