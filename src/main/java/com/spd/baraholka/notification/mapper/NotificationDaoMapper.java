package com.spd.baraholka.notification.mapper;

import com.spd.baraholka.notification.dao.NotificationDao;
import com.spd.baraholka.notification.dto.NotificationDto;

public class NotificationDaoMapper {

    public NotificationDao toNotificationDao(NotificationDto notificationDto) {
        NotificationDao notificationDao = new NotificationDao();
        notificationDao.setUserMailToId(notificationDao.getUserMailToId());
        notificationDao.setAdvertisementId(notificationDao.getAdvertisementId());
        notificationDao.setEventType(notificationDao.getEventType());
        notificationDao.setSendDate(notificationDao.getSendDate());
        return notificationDao;
    }
}
