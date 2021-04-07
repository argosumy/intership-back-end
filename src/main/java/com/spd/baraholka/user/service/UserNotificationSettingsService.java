package com.spd.baraholka.user.service;

import com.spd.baraholka.exceptions.NotFoundByIdException;
import com.spd.baraholka.user.persistance.PersistenceUserNotificationSettingsService;
import com.spd.baraholka.user.persistance.entities.UserNotificationSettings;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserNotificationSettingsService {

    private final PersistenceUserNotificationSettingsService persistenceUserNotificationSettingsService;

    public UserNotificationSettingsService(PersistenceUserNotificationSettingsService persistenceUserNotificationSettingsService) {
        this.persistenceUserNotificationSettingsService = persistenceUserNotificationSettingsService;
    }

    public UserNotificationSettings getNotificationSettingsByUserId(int userId) {
        Optional<UserNotificationSettings> userNotificationSettings = persistenceUserNotificationSettingsService.getNotificationSettingsByUserId(userId);
        if (userNotificationSettings.isEmpty()) {
            throw new NotFoundByIdException(userId);
        } else {
            return userNotificationSettings.get();
        }
    }

    public int saveNotificationSettings(UserNotificationSettings userNotificationSettings) {
        Optional<UserNotificationSettings> notificationSettings =
                persistenceUserNotificationSettingsService.getNotificationSettingsByUserId(userNotificationSettings.getUserId());
        return notificationSettings.isEmpty() ?
                persistenceUserNotificationSettingsService.saveNotificationSettings(userNotificationSettings) :
                persistenceUserNotificationSettingsService.updateNotificationSettings(userNotificationSettings, notificationSettings.get().getId());
    }
}
