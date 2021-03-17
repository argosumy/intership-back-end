package com.spd.baraholka.notifications.DAO;

import com.spd.baraholka.notifications.DAO.factory.SaveNotification;
import com.spd.baraholka.notifications.DAO.factory.SaveNotificationFactory;
import com.spd.baraholka.notifications.enume.EventTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class NotificationDAO implements DAOConnection {
private final JdbcTemplate jdbcTemplate;
private SaveNotificationFactory factory;

@Autowired
public NotificationDAO(JdbcTemplate jdbcTemplate, SaveNotificationFactory factory) {
    this.jdbcTemplate = jdbcTemplate;
    this.factory = factory;
}

    @Override
    public void saveNotification(EventTypes types, Map<String, String> arg) {
        SaveNotification saveNotification = factory.buildSaveNotification(types);
        saveNotification.save(arg, jdbcTemplate);
    }
}
