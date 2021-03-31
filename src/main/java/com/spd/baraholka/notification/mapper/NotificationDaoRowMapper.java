package com.spd.baraholka.notification.mapper;

import com.spd.baraholka.notification.model.Notification;
import com.spd.baraholka.notification.enums.EventType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class NotificationDaoRowMapper implements RowMapper<Notification> {

    @Override
    public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
        Notification notification = new Notification();
        notification.setUserMailToId(rs.getInt("user_mail_to_id"));
        notification.setAdvertisementId(rs.getInt("advertisement_id"));
        notification.setEventType(EventType.valueOf(rs.getString("event_type")));
        notification.setSendDate(rs.getTimestamp("send_date").toLocalDateTime());
        return notification;
    }
}
