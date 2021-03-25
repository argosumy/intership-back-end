package com.spd.baraholka.notification.repositorys.factory;

import com.spd.baraholka.notification.repositorys.SQLQueries;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.enums.NotificationStatus;
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
public class NotificationRepositoryChangesAd {

    public EventType getType() {
        return EventType.ADVERTISEMENT_CHANGE;
    }

    public void save(Map<String, String> args, JdbcTemplate template) {
        Date date = Date.valueOf(LocalDate.now());
        String sqlInsert = SQLQueries.SAVE_NOTIFICATION_CHANGES_AD;
        String idWhishlist = SQLQueries.GET_USER_ID_WISHLIST;
        int eventId = template.queryForObject(SQLQueries.GET_ID_EVENT_BY_NAME, Integer.class, EventType.ADVERTISEMENT_CHANGE.name());
        int statusId = template.queryForObject(SQLQueries.GET_ID_STATUS_BY_NAME, Integer.class, NotificationStatus.NEW.name());
        int adId = Integer.parseInt(args.get("adId"));
        List<Map<String, Object>> list = template.queryForList(idWhishlist, adId);
        for (Map<String, Object> id: list) {
            int recipient = (int) id.get("id");
            PreparedStatementSetter ps = new PreparedStatementSetter() {
                @SuppressWarnings("checkstyle:MagicNumber")
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, recipient);
                    ps.setInt(2, statusId);
                    ps.setInt(3, eventId);
                    ps.setDate(4, date);
                    ps.setString(5, args.get("reason"));
                    ps.setInt(6, Integer.parseInt(args.get("adId")));
                }
            };
            template.update(sqlInsert, ps);
        }
    }
}
