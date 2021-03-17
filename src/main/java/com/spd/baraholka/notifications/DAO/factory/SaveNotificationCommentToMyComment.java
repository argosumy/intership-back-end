package com.spd.baraholka.notifications.DAO.factory;

import com.spd.baraholka.notifications.DAO.SQLQueries;
import com.spd.baraholka.notifications.enume.EventTypes;
import com.spd.baraholka.notifications.enume.NotificationStatus;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

@Component
public class SaveNotificationCommentToMyComment implements SaveNotification{
    @Override
    public void save(HashMap<String, String> args, Connection connection) throws SQLException {
        try {
            Date date = Date.valueOf(LocalDate.now());
            //send_to,send_from,status,event,date
            String sqlInsert = SQLQueries.SAVE_NOTIFICATION_NEW_COMMENT_TO_MY_COMMENT;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setInt(1, Integer.parseInt(args.get("sendTo")));
            preparedStatement.setInt(2, Integer.parseInt(args.get("writer")));
            preparedStatement.setString(3,NotificationStatus.NEW.name());
            preparedStatement.setString(4,EventTypes.NEW_COMMENTS_TO_MY_COMMENTS.name());
            preparedStatement.setDate(5, date);
            preparedStatement.executeQuery();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public EventTypes getType() {
        return EventTypes.NEW_COMMENTS_TO_MY_COMMENTS;
    }
}
