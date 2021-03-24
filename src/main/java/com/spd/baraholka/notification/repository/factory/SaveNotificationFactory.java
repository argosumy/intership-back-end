package com.spd.baraholka.notification.repository.factory;

import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaveNotificationFactory {

    private final List<NotificationRepository> list;

    @Autowired
    public SaveNotificationFactory(List<NotificationRepository> list) {
        this.list = list;
    }

    public NotificationRepository buildSaveNotification(EventType types) {
        NotificationRepository notificationRepository = null;
        for (NotificationRepository object : list) {
            if (object.getType() == types) {
                notificationRepository = object;
                break;
            }
        }
        return notificationRepository;
    }
}
