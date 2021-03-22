package com.spd.baraholka.notification.DAO.rowMaper;

import com.spd.baraholka.notification.model.NotificationForDataBase;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationMaperUserIdWhishlist implements RowMapper<NotificationForDataBase> {
    @Override
    public NotificationForDataBase mapRow(ResultSet rs, int rowNum) throws SQLException {
        NotificationForDataBase notification = new NotificationForDataBase();
        notification.setRecipient(rs.getInt("recipient"));
        return notification;
    }
}
