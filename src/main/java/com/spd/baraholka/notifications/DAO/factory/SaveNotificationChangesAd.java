package com.spd.baraholka.notifications.DAO.factory;

import com.spd.baraholka.notifications.DAO.SQLQueries;
import com.spd.baraholka.notifications.enume.EventTypes;
import com.spd.baraholka.notifications.enume.NotificationStatus;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

@Component
public class SaveNotificationChangesAd implements SaveNotification{
    @Override
    public EventTypes getType() {
        return EventTypes.CHANGES_ADVERTISEMENT;
    }

    @Override
    public void save(HashMap<String, String> args, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement = connection.prepareStatement(SQLQueries.GET_USER_ID_WISHLIST);
        //table wish_list idAD == idUserOrIdAd
        preparedStatement.setInt(1, Integer.parseInt(args.get("adId")));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int sendTo = resultSet.getInt(1);
            Date date = Date.valueOf(LocalDate.now());
            String sqlInsert = SQLQueries.SAVE_NOTIFICATION_NEW_AD;

            try {
                preparedStatement = connection.prepareStatement(sqlInsert);
                preparedStatement.setInt(1,sendTo);
                preparedStatement.setString(2,NotificationStatus.NEW.name());
                preparedStatement.setString(3,EventTypes.CHANGES_ADVERTISEMENT.name());
                preparedStatement.setDate(4,date);
                preparedStatement.setString(5,args.get("adId"));
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            preparedStatement.close();
            resultSet.close();
        }
    }





}
