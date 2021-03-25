package com.spd.baraholka.notification.repositorys;

import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.repositorys.factory.SaveNotificationFactory;
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
    public void saveNotification(EventType types, Map<String, String> arg) {
        NotificationRepository notificationRepository = factory.buildSaveNotification(types);
        notificationRepository.save(arg, jdbcTemplate);
    }
}
