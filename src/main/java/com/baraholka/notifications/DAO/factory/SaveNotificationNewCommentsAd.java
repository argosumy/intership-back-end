package com.baraholka.notifications.DAO.factory;

import com.baraholka.notifications.DAO.SQLQueries;
import com.baraholka.notifications.enume.EventTypes;
import com.baraholka.notifications.enume.NotificationStatus;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
@Component
public class SaveNotificationNewCommentsAd implements SaveNotification{
    @Override
    public void save(HashMap<String, String> args, Connection connection) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_TO_SEND_COMMENT_AD);
            preparedStatement.setInt(1,Integer.parseInt(args.get("adId")));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int sendTo = resultSet.getInt("user_id");
            resultSet.close();
            Date date = Date.valueOf(LocalDate.now());
            String sqlInsert = SQLQueries.SAVE_NOTIFICATION_NEW_AD;
            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setInt(1,sendTo);
            preparedStatement.setString(2,NotificationStatus.NEW.name());
            preparedStatement.setString(3,EventTypes.NEW_COMMENTS_ADVERTISEMENT.name());
            preparedStatement.setDate(4,date);
            preparedStatement.setInt(5, Integer.parseInt(args.get("adId")));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public EventTypes getType() {
        return EventTypes.NEW_COMMENTS_ADVERTISEMENT;
    }
}
