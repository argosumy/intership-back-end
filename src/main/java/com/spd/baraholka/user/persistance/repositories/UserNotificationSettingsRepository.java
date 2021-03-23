package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserNotificationSettingsService;
import com.spd.baraholka.user.persistance.entities.UserNotificationSettings;
import com.spd.baraholka.user.persistance.mappers.UserNotificationSettingsRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class UserNotificationSettingsRepository implements PersistenceUserNotificationSettingsService {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserNotificationSettingsRowMapper userNotificationSettingsRowMapper;

    public UserNotificationSettingsRepository(NamedParameterJdbcTemplate jdbcTemplate,
                                              UserNotificationSettingsRowMapper userNotificationSettingsRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userNotificationSettingsRowMapper = userNotificationSettingsRowMapper;
    }

    @Override
    public Optional<UserNotificationSettings> getNotificationSettingsByUserId(int userId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    "SELECT * FROM users_settings WHERE user_id=:id",
                    Map.of("id", userId),
                    userNotificationSettingsRowMapper));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int saveNotificationSettings(UserNotificationSettings userNotificationSettings) {
        return jdbcTemplate.update("INSERT INTO users_settings()");
    }
}
