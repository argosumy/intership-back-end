package com.baraholka.notifications.DAO;

import com.baraholka.notifications.enume.EventTypes;

public interface DAOConnection {
    void connect() throws ClassNotFoundException;
    void disconnect();
    void saveNotification(EventTypes types, String description, int recipient);

}
