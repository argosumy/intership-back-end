package com.spd.baraholka.notification.repository;

import com.spd.baraholka.notification.model.BaseNotification;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

public class NotificationRepositoryImpl {

    private final NotificationRepository notificationRepository;

    public NotificationRepositoryImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public BaseNotification save(Map<String, String> args, JdbcTemplate template) {
        return notificationRepository.save(args, template);
    }
}
