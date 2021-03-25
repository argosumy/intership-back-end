package com.spd.baraholka.notification.mapper;

import com.spd.baraholka.notification.dao.NotificationDao;
import com.spd.baraholka.notification.enums.EventType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationDaoRowMapper implements RowMapper<NotificationDao> {

    @Override
    public NotificationDao mapRow(ResultSet rs, int rowNum) throws SQLException {
        NotificationDao notificationDao = new NotificationDao();
        notificationDao.setUserMailToId(rs.getInt("user_mail_to_id"));
        notificationDao.setAdvertisementId(rs.getInt("advertisement_id"));
        notificationDao.setEventType(EventType.valueOf(rs.getString("event_type")));
        notificationDao.setSendDate(rs.getTimestamp("send_date").toLocalDateTime());
        return notificationDao;
    }
}
