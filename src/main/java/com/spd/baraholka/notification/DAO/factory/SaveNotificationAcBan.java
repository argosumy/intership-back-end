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
public class SaveNotificationAcBan implements SaveNotification {
@Override
public EventTypes getType() {
        return EventTypes.ACCOUNT_BAN;
    }

    @Override
    public void save(Map<String, String> args, JdbcTemplate template) {
        Date date = Date.valueOf(LocalDate.now());
        String sqlInsert = SQLQueries.SAVE_NOTIFICATION_BAN;
        PreparedStatementSetter ps = new PreparedStatementSetter() {
            @SuppressWarnings("checkstyle:MagicNumber")
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, Integer.parseInt(args.get("sendTo")));
                ps.setString(2, NotificationStatus.NEW.name());
                ps.setString(3, EventTypes.ACCOUNT_BAN.name());
                ps.setDate(4, date);
                ps.setString(5, args.get("reason"));
            }
        };
        template.update(sqlInsert, ps);


//        try{
//            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
//            preparedStatement.setInt(1, Integer.parseInt(args.get("sendTo")));
//            preparedStatement.setString(2,NotificationStatus.NEW.name());
//            preparedStatement.setString(3,EventTypes.ACCOUNT_BAN.name());
//            preparedStatement.setDate(4,date);
//            preparedStatement.setString(5,args.get("reason"));
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }
}
