package com.spd.baraholka.notifications.DAO.factory;

import com.spd.baraholka.notifications.enume.EventTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class SaveNotificationNewAd implements SaveNotification{

    @Override
    public EventTypes getType() {
        return EventTypes.NEW_ADVERTISEMENT;
    }

    @Override
    public void save(Map<String, String> args, JdbcTemplate template) {
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(SQLQueries.GET_ALL_USER_ID);
//        statement.close();
//        while (resultSet.next()) {
//            int id = resultSet.getInt(1);
//            Date date = Date.valueOf(LocalDate.now());
//            String sqlInsert = SQLQueries.SAVE_NOTIFICATION_NEW_AD;
//            try {
//                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
//                preparedStatement.setInt(1,id);
//                preparedStatement.setString(2,NotificationStatus.NEW.name());
//                preparedStatement.setString(3,EventTypes.NEW_ADVERTISEMENT.name());
//                preparedStatement.setDate(4,date);
//                preparedStatement.setInt(5, Integer.parseInt(args.get("adId")));
//                preparedStatement.executeUpdate();
//                preparedStatement.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        resultSet.close();
    }
}
