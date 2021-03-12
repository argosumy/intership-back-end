package com.spd.baraholka.users.controllers;

import com.spd.baraholka.users.services.UserGeneralSettingDTO;
import com.spd.baraholka.users.services.UserGeneralSettingService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/settings/general")
public class UserGeneralSettingController {

    private final UserGeneralSettingService userGeneralSettingService;

    public UserGeneralSettingController(UserGeneralSettingService userGeneralSettingService) {
        this.userGeneralSettingService = userGeneralSettingService;
    }

    @PutMapping
    public int updateUserGeneralSettings(@RequestBody @Valid UserGeneralSettingDTO userGeneralSettingDTO) {
        return userGeneralSettingService.updateUserGeneralSettings(userGeneralSettingDTO);
    }
}
