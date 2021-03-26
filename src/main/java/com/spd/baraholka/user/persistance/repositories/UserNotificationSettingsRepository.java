package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserNotificationSettingsService;
import com.spd.baraholka.user.persistance.entities.UserNotificationSettings;
import com.spd.baraholka.user.persistance.mappers.UserNotificationSettingsRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;
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
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = getInsertedParameters(userNotificationSettings);
        jdbcTemplate.update(getInsertNotificationSettingsSql(), parameterSource, keyHolder, new String[] {"id"});
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    private String getInsertNotificationSettingsSql() {
        return "INSERT INTO users_settings(" +
                "user_id, " +
                "new_ads_notification, " +
                "new_comments_to_my_ad_notification, " +
                "replies_to_my_comments_notification, " +
                "mentions_in_thread_notification, " +
                "wishlist_update_notification) " +
                "VALUES(:user_id, :newAd, :newAdComment, :replyComment, :mentionInThread, :wishlistUpdate)";
    }

    private MapSqlParameterSource getInsertedParameters(UserNotificationSettings userNotificationSettings) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("user_id", userNotificationSettings.getUserId());
        namedParameters.addValue("newAd", userNotificationSettings.isNewAdNotification());
        namedParameters.addValue("newAdComment", userNotificationSettings.isNewCommentToAdNotification());
        namedParameters.addValue("replyComment", userNotificationSettings.isReplyToCommentNotification());
        namedParameters.addValue("mentionInThread", userNotificationSettings.isMentionInNotificationThread());
        namedParameters.addValue("wishlistUpdate", userNotificationSettings.isWishlistUpdateNotification());
        return namedParameters;
    }
}
