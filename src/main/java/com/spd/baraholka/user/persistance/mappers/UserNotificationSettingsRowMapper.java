package com.spd.baraholka.user.persistance.mappers;

import com.spd.baraholka.user.persistance.entities.UserNotificationSettings;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserNotificationSettingsRowMapper implements RowMapper<UserNotificationSettings> {

    @Override
    public UserNotificationSettings mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserNotificationSettings userNotificationSettings = new UserNotificationSettings();
        userNotificationSettings.setId(rs.getInt("id"));
        userNotificationSettings.setUserId(rs.getInt("user_id"));
        userNotificationSettings.setNewAdNotification(rs.getBoolean("new_ads_notification"));
        userNotificationSettings.setNewCommentToAdNotification(rs.getBoolean("new_comments_to_my_ad_notification"));
        userNotificationSettings.setReplyToCommentNotification(rs.getBoolean("replies_to_my_comments_notification"));
        userNotificationSettings.setWishlistUpdateNotification(rs.getBoolean("wishlist_update_notification"));
        return userNotificationSettings;
    }
}
