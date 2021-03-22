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
import java.util.List;
import java.util.Map;

@Component
public class SaveNotificationNewAd implements SaveNotification {

    @Override
    public EventTypes getType() {
        return EventTypes.NEW_ADVERTISEMENT;
    }

    @Override
    public void save(Map<String, String> args, JdbcTemplate template) {
        Date date = Date.valueOf(LocalDate.now());
        String category = args.get("category");
        int eventId = template.queryForObject(SQLQueries.GET_ID_EVENT_BY_NAME, Integer.class, EventTypes.NEW_COMMENTS_TO_MY_COMMENTS.name());
        int statusId = template.queryForObject(SQLQueries.GET_ID_STATUS_BY_NAME, Integer.class, NotificationStatus.NEW.name());
        int adId = Integer.parseInt(args.get("adId"));
        List<Map<String, Object>> list = template.queryForList(SQLQueries.GET_USER_ID_BY_TERMS_SUBSCRIPTION, category);
        for (Map<String, Object> id: list) {
            int recipient = (int) id.get("user_id");
            PreparedStatementSetter ps = new PreparedStatementSetter() {
                @SuppressWarnings("checkstyle:MagicNumber")
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, recipient);
                    ps.setInt(2, statusId);
                    ps.setInt(3, eventId);
                    ps.setDate(4, date);
                    ps.setInt(5, adId);
                }
            };
            template.update(SQLQueries.SAVE_NOTIFICATION_NEW_AD, ps);
        }
    }
}
