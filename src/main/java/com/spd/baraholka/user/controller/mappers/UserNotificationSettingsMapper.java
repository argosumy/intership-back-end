package com.spd.baraholka.user.controller.mappers;

import com.spd.baraholka.user.controller.dto.UserNotificationSettingsDTO;
import com.spd.baraholka.user.persistance.entities.UserNotificationSettings;
import org.springframework.stereotype.Component;

@Component
public class UserNotificationSettingsMapper {

    public UserNotificationSettingsDTO convertToDTO(UserNotificationSettings userNotificationSettings) {
        UserNotificationSettingsDTO userNotificationSettingsDto = new UserNotificationSettingsDTO();
        userNotificationSettingsDto.setId(userNotificationSettings.getId());
        userNotificationSettingsDto.setUserId(userNotificationSettings.getUserId());
        userNotificationSettingsDto.setNewAdNotification(userNotificationSettings.isNewAdNotification());
        userNotificationSettingsDto.setNewCommentToAdNotification(userNotificationSettings.isNewCommentToAdNotification());
        userNotificationSettingsDto.setReplyToCommentNotification(userNotificationSettings.isReplyToCommentNotification());
        userNotificationSettingsDto.setWishlistUpdateNotification(userNotificationSettings.isWishlistUpdateNotification());
        return userNotificationSettingsDto;
    }

    public UserNotificationSettings convertToEntity(UserNotificationSettingsDTO userNotificationSettingsDto) {
        UserNotificationSettings userNotificationSettings = new UserNotificationSettings();
        userNotificationSettings.setId(userNotificationSettingsDto.getId());
        userNotificationSettings.setUserId(userNotificationSettingsDto.getUserId());
        userNotificationSettings.setNewAdNotification(userNotificationSettingsDto.isNewAdNotification());
        userNotificationSettings.setNewCommentToAdNotification(userNotificationSettingsDto.isNewCommentToAdNotification());
        userNotificationSettings.setReplyToCommentNotification(userNotificationSettingsDto.isReplyToCommentNotification());
        userNotificationSettings.setWishlistUpdateNotification(userNotificationSettingsDto.isWishlistUpdateNotification());
        return userNotificationSettings;
    }
}
