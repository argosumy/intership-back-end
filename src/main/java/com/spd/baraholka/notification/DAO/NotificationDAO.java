package com.spd.baraholka.notification.DAO;

import com.spd.baraholka.notification.enums.EventTypes;

import java.util.Map;

public interface NotificationDAO {
    void saveNotification(EventTypes types, Map<String, String> arg);
}
