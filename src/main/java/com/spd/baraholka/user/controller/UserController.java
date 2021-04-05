package com.spd.baraholka.user.controller;

import com.spd.baraholka.annotation.user.UserExist;
import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.service.UserService;
import com.spd.baraholka.user.service.UserSettingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;
    private final UserSettingsService userSettingsService;

    public UserController(UserService userService, UserSettingsService userSettingsService) {
        this.userService = userService;
        this.userSettingsService = userSettingsService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") @UserExist int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/general")
    public int updateUserGeneralSettings(@PathVariable("id") @UserExist int id, @RequestParam("openAdsInNewTab") boolean openAdsInNewTab) {
        return userSettingsService.updateUserGeneralSettings(id, openAdsInNewTab);
    }

    @GetMapping
    public List<UserShortViewDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> showMe() {
        UserDTO userDTO = userService.getCurrentUserDTO();
        return ResponseEntity.ok().body(userDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/{id}/roles")
    public void grantRole(@PathVariable("id") @UserExist int id, @RequestParam Role role) {
        User user = userService.getUserEntityById(id);
        userService.grantRole(user, role);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}/roles")
    public void revokeRole(@PathVariable("id") @UserExist int id, @RequestParam Role role) {
        User user = userService.getUserEntityById(id);
        userService.revokeRole(user, role);
    }
}
