package com.spd.baraholka.notification.mapper;

import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.Notification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapper {

    public Notification of(int userId, int advertisementId, EventType eventType) {
        Notification notification = new Notification();
        notification.setUserMailToId(userId);
        notification.setAdvertisementId(advertisementId);
        notification.setEventType(eventType);
        notification.setSendDate(LocalDateTime.now());
        return notification;
    }
}
