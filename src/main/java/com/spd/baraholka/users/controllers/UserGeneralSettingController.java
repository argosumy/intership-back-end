package com.spd.baraholka.users.controllers;

import com.spd.baraholka.users.services.UserGeneralSettingService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserGeneralSettingController {

    private final UserGeneralSettingService userGeneralSettingService;

    public UserGeneralSettingController(UserGeneralSettingService userGeneralSettingService) {
        this.userGeneralSettingService = userGeneralSettingService;
    }
}
