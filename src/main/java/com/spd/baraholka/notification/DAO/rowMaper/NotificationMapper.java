package com.spd.baraholka.notification.DAO.rowMaper;

import com.spd.baraholka.notification.model.NotificationForDataBase;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class NotificationMapper implements RowMapper<NotificationForDataBase> {
    @Override
    public NotificationForDataBase mapRow(ResultSet rs, int rowNum) throws SQLException {
        NotificationForDataBase notification = new NotificationForDataBase();
        notification.setId(rs.getInt("id"));
        notification.setDescription(rs.getString("description"));
        notification.setRecipient(rs.getInt("recipient"));
        notification.setWriter(rs.getInt("writer"));
        notification.setAdvertisement(rs.getInt("adId"));
        notification.setStatus(rs.getInt("status"));
        notification.setEvent(rs.getInt("event"));
        notification.setDate(rs.getDate("date"));
        return notification;
    }
}
