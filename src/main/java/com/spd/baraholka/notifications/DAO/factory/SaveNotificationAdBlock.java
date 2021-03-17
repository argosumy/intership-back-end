package com.spd.baraholka.notifications.DAO.factory;

import com.spd.baraholka.notifications.DAO.SQLQueries;
import com.spd.baraholka.notifications.enume.EventTypes;
import com.spd.baraholka.notifications.enume.NotificationStatus;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

@Component
public class SaveNotificationAdBlock implements SaveNotification {

    @Override
    public void save(HashMap<String, String> args, Connection connection) throws SQLException {
        Date date = Date.valueOf(LocalDate.now());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_TO_SEND_BLOCK_AD);
            preparedStatement.setInt(1,Integer.parseInt(args.get("adId")));
            ResultSet resultSet = preparedStatement.executeQuery();
            int sendTo = resultSet.getInt(1);
            resultSet.close();

            preparedStatement = connection.prepareStatement(SQLQueries.SAVE_NOTIFICATION_BLOCK_AD);
            preparedStatement.setInt(1, sendTo);
            preparedStatement.setString(2, NotificationStatus.NEW.name());
            preparedStatement.setString(3,EventTypes.ADVERTISEMENT_BLOCK.name());
            preparedStatement.setDate(4,date);
            preparedStatement.setString(5,args.get("reason"));
            preparedStatement.setInt(6, Integer.parseInt(args.get("adId")));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public EventTypes getType() {
        return EventTypes.ADVERTISEMENT_BLOCK;
    }
}
