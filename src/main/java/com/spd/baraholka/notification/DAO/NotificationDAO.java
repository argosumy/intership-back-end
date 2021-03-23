package com.spd.baraholka.notification.DAO;

import com.spd.baraholka.notification.enumes.EventTypes;

import java.util.Map;

public interface NotificationDAO {
    void saveNotification(EventTypes types, Map<String, String> arg);
}
