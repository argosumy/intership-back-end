package com.spd.baraholka.notification.repository;

import com.spd.baraholka.notification.model.Notification;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class NotificationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public NotificationRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveNotification(Notification notification) {
        return jdbcTemplate.update(
                "INSERT INTO notifications(user_mail_to_id, advertisement_id, event_type, send_date)" +
                        "VALUES(:mailTo, :adId, :eventType, :sendDate)",
                Map.of("mailTo", notification.getUserMailToId(),
                        "adId", notification.getAdvertisementId(),
                        "eventType", notification.getEventType().name(),
                        "sendDate", notification.getSendDate())
        );
    }
}
