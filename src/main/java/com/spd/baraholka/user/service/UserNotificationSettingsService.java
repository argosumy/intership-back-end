package com.spd.baraholka.user.service;

import com.spd.baraholka.config.exceptions.NotFoundByIdException;
import com.spd.baraholka.user.persistance.PersistenceUserNotificationSettingsService;
import com.spd.baraholka.user.persistance.entities.UserNotificationSettings;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserNotificationSettingsService {

    private final PersistenceUserNotificationSettingsService userNotificationSettingsService;

    public UserNotificationSettingsService(PersistenceUserNotificationSettingsService userNotificationSettingsService) {
        this.userNotificationSettingsService = userNotificationSettingsService;
    }

    public Optional<UserNotificationSettings> getNotificationSettingsByUserId(int userId) {
        Optional<UserNotificationSettings> userNotificationSettings = userNotificationSettingsService.getNotificationSettingsByUserId(userId);
        if (userNotificationSettings.isEmpty()) {
            throw new NotFoundByIdException(userId);
        } else {
            return userNotificationSettings;
        }
    }

    public int saveNotificationSettings(UserNotificationSettings userNotificationSettings) {
        return userNotificationSettingsService.saveNotificationSettings(userNotificationSettings);
    }
}
