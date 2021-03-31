package com.spd.baraholka.notification.mapper;

import com.spd.baraholka.notification.model.Notification;
import com.spd.baraholka.notification.dto.NotificationDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationDaoMapper {

    public Notification toNotification(NotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setUserMailToId(notificationDto.getUserMailToId());
        notification.setAdvertisementId(notificationDto.getAdvertisementId());
        notification.setEventType(notificationDto.getEventType());
        notification.setSendDate(LocalDateTime.now());
        return notification;
    }
}
