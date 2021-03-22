package com.spd.baraholka.notification.DAO.factory;

import com.spd.baraholka.notification.enumes.EventTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.Map;

@SuppressWarnings("checkstyle:WhitespaceAround")
@Component
public class SaveNotificationCommentToMyComment implements SaveNotification{
    @Override
    public void save(Map<String, String> args, JdbcTemplate template) {
//        try {
//            Date date = Date.valueOf(LocalDate.now());
//            //send_to,send_from,status,event,date
//            String sqlInsert = SQLQueries.SAVE_NOTIFICATION_NEW_COMMENT_TO_MY_COMMENT;
//            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
//            preparedStatement.setInt(1, Integer.parseInt(args.get("sendTo")));
//            preparedStatement.setInt(2, Integer.parseInt(args.get("writer")));
//            preparedStatement.setString(3,NotificationStatus.NEW.name());
//            preparedStatement.setString(4,EventTypes.NEW_COMMENTS_TO_MY_COMMENTS.name());
//            preparedStatement.setDate(5, date);
//            preparedStatement.executeQuery();
//            preparedStatement.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    @Override
    public EventTypes getType() {
        return EventTypes.NEW_COMMENTS_TO_MY_COMMENTS;
    }
}
