package com.spd.baraholka.notification.DAO.factory;

import com.spd.baraholka.notification.enumes.EventTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Map;

public interface SaveNotification {

    public void save(Map<String, String> args, JdbcTemplate template);
    public EventTypes getType();
}
