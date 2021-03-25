package com.spd.baraholka.notification.repository;

import com.spd.baraholka.notification.dao.NotificationDao;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

public class NotificationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public NotificationRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveNotification(NotificationDao notificationDao) {
        return jdbcTemplate.update(
                "INSERT INTO notifications(user_mail_to_id, advertisement_id, event_type, send_date)" +
                        "VALUES(:mailTo, :adId, :eventType, :sendDate)",
                Map.of("userMailToId", notificationDao.getUserMailToId(),
                        "advertisement_id", notificationDao.getAdvertisementId(),
                        "event_type", notificationDao.getEventType().name(),
                        "send_date", notificationDao.getSendDate())
        );
    }

    public List<NotificationDao> saveNotification(NotificationDao notificationDao) {
        return jdbcTemplate.query(
                "INSERT INTO notifications(user_mail_to_id, advertisement_id, event_type, send_date)" +
                        "VALUES(:mailTo, :adId, :eventType, :sendDate)",
                Map.of("userMailToId", notificationDao.getUserMailToId(),
                        "advertisement_id", notificationDao.getAdvertisementId(),
                        "event_type", notificationDao.getEventType().name(),
                        "send_date", notificationDao.getSendDate())
        );
    }
}
