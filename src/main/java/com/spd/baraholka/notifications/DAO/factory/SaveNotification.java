package com.spd.baraholka.notifications.DAO.factory;

import com.spd.baraholka.notifications.enume.EventTypes;

import java.sql.*;
import java.util.HashMap;

public interface SaveNotification {
    public void save(HashMap<String,String> args, Connection connection) throws SQLException;
    public EventTypes getType();


}
