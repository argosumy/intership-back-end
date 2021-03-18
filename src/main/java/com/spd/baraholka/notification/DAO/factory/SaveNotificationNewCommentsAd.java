package com.spd.baraholka.notification.DAO.factory;

import com.spd.baraholka.notification.enumes.EventTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class SaveNotificationNewCommentsAd implements SaveNotification {
    @Override
    public void save(Map<String, String> args, JdbcTemplate template) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_TO_SEND_COMMENT_AD);
//            preparedStatement.setInt(1,Integer.parseInt(args.get("adId")));
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            int sendTo = resultSet.getInt("user_id");
//            resultSet.close();
//            Date date = Date.valueOf(LocalDate.now());
//            String sqlInsert = SQLQueries.SAVE_NOTIFICATION_NEW_AD;
//            preparedStatement = connection.prepareStatement(sqlInsert);
//            preparedStatement.setInt(1, sendTo);
//            preparedStatement.setString(2, NotificationStatus.NEW.name());
//            preparedStatement.setString(3, EventTypes.NEW_COMMENTS_ADVERTISEMENT.name());
//            preparedStatement.setDate(4, date);
//            preparedStatement.setInt(5, Integer.parseInt(args.get("adId")));
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    @Override
    public EventTypes getType() {
        return EventTypes.NEW_COMMENTS_ADVERTISEMENT;
    }
}
