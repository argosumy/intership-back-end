package com.spd.baraholka.notifications.DAO.factory;

import com.spd.baraholka.notifications.enume.EventTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Map;

public interface SaveNotification {

    public void save(Map<String, String> args, JdbcTemplate template);
    public EventTypes getType();
}
