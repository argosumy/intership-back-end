//package com.spd.baraholka.notification.mapper;
//
//import com.spd.baraholka.notification.model.BaseNotification;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class NotificationRowMapper implements RowMapper<BaseNotification> {
//
//    @Override
//    public BaseNotification mapRow(ResultSet rs, int rowNum) throws SQLException {
//        BaseNotification notification = new BaseNotification();
//        notification.setId(rs.getInt("id"));
//        notification.setDescription(rs.getString("description"));
//        notification.setRecipient(rs.getInt("recipient"));
//        notification.setWriter(rs.getInt("writer"));
//        notification.setAdvertisement(rs.getInt("advertisement"));
//        notification.setStatus(rs.getInt("status"));
//        notification.setEvent(rs.getInt("event"));
//        notification.setLocalDateTime(rs.getTimestamp("date").toLocalDateTime());
//        return notification;
//    }
//}
