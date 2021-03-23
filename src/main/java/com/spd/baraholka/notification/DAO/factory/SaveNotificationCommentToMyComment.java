package com.spd.baraholka.notification.DAO.factory;

import com.spd.baraholka.notification.DAO.SQLQueries;
import com.spd.baraholka.notification.enums.EventTypes;
import com.spd.baraholka.notification.enums.NotificationStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

@SuppressWarnings("checkstyle:WhitespaceAround")
@Component
public class SaveNotificationCommentToMyComment implements SaveNotification{
    @Override
    public void save(Map<String, String> args, JdbcTemplate template) {
        Date date = Date.valueOf(LocalDate.now());
        int idComment = Integer.parseInt("idComment");
        int recipientId = template.queryForObject(SQLQueries.GET_USER_ID_BY_COMMENT, Integer.class, idComment);
        int eventId = template.queryForObject(SQLQueries.GET_ID_EVENT_BY_NAME, Integer.class, EventTypes.NEW_COMMENTS_TO_MY_COMMENTS.name());
        int statusId = template.queryForObject(SQLQueries.GET_ID_STATUS_BY_NAME, Integer.class, NotificationStatus.NEW.name());
        int writerId = Integer.parseInt(args.get("writer"));
        PreparedStatementSetter ps = new PreparedStatementSetter() {
            @SuppressWarnings("checkstyle:MagicNumber")
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, recipientId);
                ps.setInt(2, writerId);
                ps.setInt(3, statusId);
                ps.setInt(4, eventId);
                ps.setDate(5, date);
            }
        };
        template.update(SQLQueries.SAVE_NOTIFICATION_NEW_COMMENT_TO_MY_COMMENT, ps);
    }

    @Override
    public EventTypes getType() {
        return EventTypes.NEW_COMMENTS_TO_MY_COMMENTS;
    }
}
