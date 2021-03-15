package com.baraholka.notifications.DAO.factory;

import com.baraholka.notifications.DAO.SQLQueries;
import com.baraholka.notifications.enume.EventTypes;
import com.baraholka.notifications.enume.NotificationStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

public interface SaveNotification {
    public void save(HashMap<String,String> args, Connection connection) throws SQLException;
    public EventTypes getType();


}
