package com.baraholka.notifications.DAO.factory;

import com.baraholka.notifications.DAO.SQLQueries;
import com.baraholka.notifications.enume.EventTypes;
import com.baraholka.notifications.enume.NotificationStatus;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

@Component
public class SaveNotificationAcBan implements SaveNotification{


    @Override
    public EventTypes getType() {
        return EventTypes.ACCOUNT_BAN;
    }

    @Override
    public void save(HashMap<String, String> args, Connection connection) throws SQLException {
        Date date = Date.valueOf(LocalDate.now());
        String sqlInsert = SQLQueries.SAVE_NOTIFICATION_BAN;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setInt(1, Integer.parseInt(args.get("sendTo")));
            preparedStatement.setString(2,NotificationStatus.NEW.name());
            preparedStatement.setString(3,EventTypes.ACCOUNT_BAN.name());
            preparedStatement.setDate(4,date);
            preparedStatement.setString(5,args.get("reason"));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
