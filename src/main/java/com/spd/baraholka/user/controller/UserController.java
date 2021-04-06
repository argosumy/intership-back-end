package com.spd.baraholka.user.controller;

import com.spd.baraholka.annotation.user.UserExist;
import com.spd.baraholka.user.controller.dto.EditUserMainInfoDTO;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.service.UserService;
import com.spd.baraholka.user.service.UserSettingsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserProfileService userService;

    public UserController(UserService userService, UserSettingsService userSettingsService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") @UserExist int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/settings/general")
    @PutMapping
    public EditUserMainInfoDTO updateUserMainInfo(@RequestBody @Valid EditUserMainInfoDTO mainInfoDTO) {
        return userService.updateUserMainInfo(mainInfoDTO);
    }

    @GetMapping
    public List<UserShortViewDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/settings/blocking")
    public FullBlockDetailDTO blockUser(@RequestBody @Valid BlockDetailDTO blockDetailDTO) {
        return userService.blockUser(blockDetailDTO);
    }

    @PutMapping("/settings/blocking/{id}")
    public ShortViewBlockDetailDTO unBlockUser(@PathVariable("id") @UserExist int userId, @RequestParam("isNotify") boolean isNotify) {
        return userService.unBlockUser(userId, isNotify);
    }
}
