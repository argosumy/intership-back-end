package com.spd.baraholka.notification.repository;

import com.spd.baraholka.notification.dao.NotificationDao;
import com.spd.baraholka.notification.mapper.NotificationDaoRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NotificationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final NotificationDaoRowMapper notificationDaoRowMapper;

    public NotificationRepository(NamedParameterJdbcTemplate jdbcTemplate, NotificationDaoRowMapper notificationDaoRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.notificationDaoRowMapper = notificationDaoRowMapper;
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

    public List<NotificationDao> getNotifications() {
        return jdbcTemplate.query(
                "SELECT * notifications",
                notificationDaoRowMapper
        );
    }
}
