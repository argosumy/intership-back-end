package com.spd.baraholka.user.controller;

import com.spd.baraholka.user.controller.dto.UserNotificationSettingsDTO;
import com.spd.baraholka.user.controller.mappers.UserNotificationSettingsMapper;
import com.spd.baraholka.user.persistance.entities.UserNotificationSettings;
import com.spd.baraholka.user.service.UserNotificationSettingsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/settings/notification")
public class UserNotificationSettingsController {

    private final UserNotificationSettingsService userNotificationSettingsService;
    private final UserNotificationSettingsMapper userNotificationSettingsMapper;

    public UserNotificationSettingsController(UserNotificationSettingsService userNotificationSettingsService,
                                              UserNotificationSettingsMapper userNotificationSettingsMapper) {
        this.userNotificationSettingsService = userNotificationSettingsService;
        this.userNotificationSettingsMapper = userNotificationSettingsMapper;
    }

    @GetMapping("/{userId}")
    public UserNotificationSettingsDTO getNotificationSettingsByUserId(@PathVariable("userId") int userId) {
        UserNotificationSettings userNotificationSettings = userNotificationSettingsService.getNotificationSettingsByUserId(userId);
        return userNotificationSettingsMapper.convertToDTO(userNotificationSettings);
    }

    @PostMapping()
    public int saveNotificationSettings(UserNotificationSettingsDTO userNotificationSettingsDto) {
        UserNotificationSettings userNotificationSettings = userNotificationSettingsMapper.convertToEntity(userNotificationSettingsDto);
        return userNotificationSettingsService.saveNotificationSettings(userNotificationSettings);
    }
}
