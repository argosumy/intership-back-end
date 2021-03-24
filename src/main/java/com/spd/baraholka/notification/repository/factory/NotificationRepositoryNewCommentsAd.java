package com.spd.baraholka.notification.repository.factory;

import com.spd.baraholka.notification.repository.NotificationRepository;
import com.spd.baraholka.notification.repository.SQLQueries;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.enums.NotificationStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

@Component
public class NotificationRepositoryNewCommentsAd {
    public void save(Map<String, String> args, JdbcTemplate template) {
        Date date = Date.valueOf(LocalDate.now());
        int recipientId = template.queryForObject(SQLQueries.GET_USER_ID_TO_SEND_ACTION_AD, Integer.class, args.get("adId"));
        int eventId = template.queryForObject(SQLQueries.GET_ID_EVENT_BY_NAME, Integer.class, EventType.NEW_ADVERTISEMENT_COMMENT.name());
        int statusId = template.queryForObject(SQLQueries.GET_ID_STATUS_BY_NAME, Integer.class, NotificationStatus.NEW.name());
        int writerId = Integer.parseInt(args.get("writer"));
        int adId = Integer.parseInt(args.get("adId"));
        PreparedStatementSetter ps = new PreparedStatementSetter() {
            @SuppressWarnings("checkstyle:MagicNumber")
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, recipientId);
                ps.setInt(2, writerId);
                ps.setInt(3, statusId);
                ps.setInt(4, eventId);
                ps.setDate(5, date);
                ps.setInt(6, adId);
            }
        };
        template.update(SQLQueries.SAVE_NOTIFICATION_COMMENT_TO_AD, ps);
    }

    public EventType getType() {
        return EventType.NEW_ADVERTISEMENT_COMMENT;
    }
}
