package com.spd.baraholka.notification.DAO.factory;

import com.spd.baraholka.notification.enums.EventTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Map;

public interface SaveNotification {

    void save(Map<String, String> args, JdbcTemplate template);
    @SuppressWarnings("checkstyle:EmptyLineSeparator")
    EventTypes getType();
}
