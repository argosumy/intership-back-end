package com.spd.baraholka.notification.mapper;

import com.spd.baraholka.notification.dto.NotificationDto;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.BaseNotification;
import com.spd.baraholka.notification.model.Notification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapper {

    public Notification toNotification(NotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setUserMailToId(notificationDto.getUserMailToId());
        notification.setAdvertisementId(notificationDto.getAdvertisementId());
        notification.setEventType(notificationDto.getEventType());
        notification.setSendDate(LocalDateTime.now());
        return notification;
    }

    public Notification of(int userId, int advertisementId, EventType eventType) {
        Notification notification = new Notification();
        notification.setUserMailToId(userId);
        notification.setAdvertisementId(advertisementId);
        notification.setEventType(eventType);
        notification.setSendDate(LocalDateTime.now());
        return notification;
    }
}
