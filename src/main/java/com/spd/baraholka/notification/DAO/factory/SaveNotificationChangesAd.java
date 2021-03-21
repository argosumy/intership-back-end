package com.spd.baraholka.notification.DAO.factory;

import com.spd.baraholka.notification.DAO.SQLQueries;
import com.spd.baraholka.notification.enumes.EventTypes;
import com.spd.baraholka.notification.enumes.NotificationStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

@Component
public class SaveNotificationChangesAd implements SaveNotification {

    @Override
    public EventTypes getType() {
        return EventTypes.CHANGES_ADVERTISEMENT;
    }

    @Override
    public void save(Map<String, String> args, JdbcTemplate template) {
        Date date = Date.valueOf(LocalDate.now());
        String sqlInsert = SQLQueries.SAVE_NOTIFICATION_AD;
        PreparedStatementSetter ps = new PreparedStatementSetter() {
            @SuppressWarnings("checkstyle:MagicNumber")
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, Integer.parseInt(args.get("adId")));
            }
        };
     //   template.execute(SQLQueries.GET_USER_ID_WISHLIST, ps);



//        PreparedStatement preparedStatement = preparedStatement = connection.prepareStatement(SQLQueries.GET_USER_ID_WISHLIST);
//        //table wish_list idAD == idUserOrIdAd
//        preparedStatement.setInt(1, Integer.parseInt(args.get("adId")));
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()) {
//            int sendTo = resultSet.getInt(1);
//            Date date = Date.valueOf(LocalDate.now());
//            String sqlInsert = SQLQueries.SAVE_NOTIFICATION_NEW_AD;
//
//            try {
//                preparedStatement = connection.prepareStatement(sqlInsert);
//                preparedStatement.setInt(1,sendTo);
//                preparedStatement.setString(2,NotificationStatus.NEW.name());
//                preparedStatement.setString(3,EventTypes.CHANGES_ADVERTISEMENT.name());
//                preparedStatement.setDate(4,date);
//                preparedStatement.setString(5,args.get("adId"));
//                preparedStatement.executeUpdate();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//            preparedStatement.close();
//            resultSet.close();
//        }
    }
}
