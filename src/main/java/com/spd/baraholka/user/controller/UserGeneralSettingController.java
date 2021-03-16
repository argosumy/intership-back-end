package com.spd.baraholka.user.controller;

import com.spd.baraholka.user.controller.dto.UserGeneralSettingDTO;
import com.spd.baraholka.user.service.UserGeneralSettingService;
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
