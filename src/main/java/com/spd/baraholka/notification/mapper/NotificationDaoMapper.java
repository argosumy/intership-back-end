package com.spd.baraholka.notification.mapper;

import com.spd.baraholka.notification.dao.NotificationDao;
import com.spd.baraholka.notification.dto.NotificationDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationDaoMapper {

    public NotificationDao toNotificationDao(NotificationDto notificationDto) {
        NotificationDao notificationDao = new NotificationDao();
        notificationDao.setUserMailToId(notificationDto.getUserMailToId());
        notificationDao.setAdvertisementId(notificationDto.getAdvertisementId());
        notificationDao.setEventType(notificationDto.getEventType());
        notificationDao.setSendDate(LocalDateTime.now());
        return notificationDao;
    }
}
