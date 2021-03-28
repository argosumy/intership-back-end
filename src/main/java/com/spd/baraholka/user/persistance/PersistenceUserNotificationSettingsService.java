package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.persistance.entities.UserNotificationSettings;

import java.util.Optional;

public interface PersistenceUserNotificationSettingsService {

    Optional<UserNotificationSettings> getNotificationSettingsByUserId(int userId);

    int saveNotificationSettings(UserNotificationSettings userNotificationSettings);

    int updateNotificationSettings(UserNotificationSettings userNotificationSettings);
}