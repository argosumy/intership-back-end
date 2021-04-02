package com.spd.baraholka.user.controller;

import com.spd.baraholka.annotation.user.UserExist;
import com.spd.baraholka.user.controller.dto.*;
import com.spd.baraholka.user.service.UserProfileService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserProfileService userService;

    public UserController(UserProfileService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") @UserExist int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/settings/general")
    public int updateUserGeneralSettings(@PathVariable("id") @UserExist int id, @RequestParam("openAdsInNewTab") boolean openAdsInNewTab) {
        return userService.updateUserGeneralSettings(id, openAdsInNewTab);
    }

    @GetMapping
    public List<UserShortViewDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/settings/block")
    public FullBlockDetailDTO blockUser(@RequestBody @Valid BlockDetailDTO blockDetailDTO) {
        return userService.blockUser(blockDetailDTO);
    }

    @PutMapping("/settings/block/{id}")
    public ShortViewBlockDetailDTO unBlockUser(@PathVariable("id") @UserExist int userId, @RequestParam("isNotify") boolean isNotify) {
        return userService.unBlockUser(userId, isNotify);
    }
}
