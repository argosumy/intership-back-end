package com.spd.baraholka.notifications.DAO;

import com.spd.baraholka.notifications.enume.EventTypes;

import java.util.HashMap;

public interface DAOConnection {
    void connect() throws ClassNotFoundException;
    void saveNotification(EventTypes types, HashMap<String,String> arg);

}
