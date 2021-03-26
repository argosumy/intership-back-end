package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.mappers.UserNotificationSettingsMapper;
import com.spd.baraholka.user.persistance.PersistenceUserNotificationSettingsService;
import com.spd.baraholka.user.persistance.entities.UserNotificationSettings;
import com.spd.baraholka.user.persistance.repositories.UserNotificationSettingsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserNotificationSettingsServiceTest {

    @Mock
    private PersistenceUserNotificationSettingsService persistenceUserNotificationSettingsService;
    @Mock
    private UserNotificationSettingsRepository userNotificationSettingsRepository;
    private UserNotificationSettingsService userNotificationSettingsService;
    private UserNotificationSettings userNotificationSettings;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        UserNotificationSettingsMapper userNotificationSettingsMapper = new UserNotificationSettingsMapper();
        userNotificationSettingsService = new UserNotificationSettingsService(persistenceUserNotificationSettingsService);
        userNotificationSettings = createUserNotificationSettings();
    }

    private UserNotificationSettings createUserNotificationSettings() {
        UserNotificationSettings userNotificationSettings = new UserNotificationSettings;
        userNotificationSettings.setUserId(1);
        userNotificationSettings.setNewAdNotification(true);
        userNotificationSettings.setNewCommentToAdNotification(true);
        userNotificationSettings.setMentionInNotificationThread(true);
        userNotificationSettings.setReplyToCommentNotification(true);
        userNotificationSettings.setWishlistUpdateNotification(true);
        return userNotificationSettings;
    }

    @Test
    void getNotificationSettingsByUserId() {
    }

    @Test
    @DisplayName("Should correctly save notification settings and return its id")
    void saveNotificationSettings() {
        when(userNotificationSettingsService.saveNotificationSettings(userNotificationSettings)).thenReturn(1);
        int returnedId = userNotificationSettingsService.saveNotificationSettings(userNotificationSettings);

        assertThat(returnedId).isEqualTo(1);
    }

}