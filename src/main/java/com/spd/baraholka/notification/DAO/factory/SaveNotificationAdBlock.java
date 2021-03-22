package com.spd.baraholka.notification.DAO.factory;

import com.spd.baraholka.notification.DAO.SQLQueries;
import com.spd.baraholka.notification.enumes.EventTypes;
import com.spd.baraholka.notification.enumes.NotificationStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.Map;

@Component
public class SaveNotificationAdBlock implements SaveNotification {
    @Override
    public void save(Map<String, String> args, JdbcTemplate template) {
        Date date = Date.valueOf(LocalDate.now());
        String sqlInsert = SQLQueries.SAVE_NOTIFICATION_AD;
        int eventId = template.queryForObject(SQLQueries.GET_ID_EVENT_BY_NAME, Integer.class, EventTypes.ADVERTISEMENT_BLOCK.name());
        int statusId = template.queryForObject(SQLQueries.GET_ID_STATUS_BY_NAME, Integer.class, NotificationStatus.NEW.name());
        PreparedStatementSetter ps = new PreparedStatementSetter() {
            @SuppressWarnings("checkstyle:MagicNumber")
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, Integer.parseInt(args.get("sendTo")));
                ps.setInt(2, statusId);
                ps.setInt(3, eventId);
                ps.setDate(4, date);
                ps.setString(5, args.get("reason"));
                ps.setInt(6, Integer.parseInt(args.get("adId")));
            }
        };
        template.update(sqlInsert, ps);
    }

    @Override
    public EventTypes getType() {
        return EventTypes.ADVERTISEMENT_BLOCK;
    }
}
