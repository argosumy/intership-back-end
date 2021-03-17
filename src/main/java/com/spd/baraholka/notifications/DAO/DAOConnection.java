package com.spd.baraholka.notifications.DAO;

import com.spd.baraholka.notifications.enume.EventTypes;

import java.util.Map;

public interface DAOConnection {
    void saveNotification(EventTypes types, Map<String, String> arg);
}
