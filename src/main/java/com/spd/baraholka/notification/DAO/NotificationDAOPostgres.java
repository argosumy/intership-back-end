package com.spd.baraholka.notification.DAO;

import com.spd.baraholka.notification.DAO.factory.SaveNotification;
import com.spd.baraholka.notification.DAO.factory.SaveNotificationFactory;
import com.spd.baraholka.notification.enums.EventTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class NotificationDAOPostgres implements NotificationDAO {
private final JdbcTemplate jdbcTemplate;
private final SaveNotificationFactory factory;

@Autowired
public NotificationDAOPostgres(JdbcTemplate jdbcTemplate, SaveNotificationFactory factory) {
    this.jdbcTemplate = jdbcTemplate;
    this.factory = factory;
}

    @Override
    public void saveNotification(EventTypes types, Map<String, String> arg) {
        SaveNotification saveNotification = factory.buildSaveNotification(types);
        saveNotification.save(arg, jdbcTemplate);
    }
}
