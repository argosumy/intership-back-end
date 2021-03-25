package com.spd.baraholka.notification.repositorys;

import com.spd.baraholka.notification.enums.EventType;

import java.util.Map;

public interface NotificationDAO {
    void saveNotification(EventType types, Map<String, String> arg);
}
