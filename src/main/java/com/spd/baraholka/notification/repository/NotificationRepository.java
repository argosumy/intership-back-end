package com.spd.baraholka.notification.repository;

import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.BaseNotification;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Map;

public interface NotificationRepository {

    BaseNotification save(Map<String, String> args, JdbcTemplate template);

    EventType getType();
}
