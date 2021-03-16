package com.spd.baraholka.user.controller;

import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/{isBlocked}")
    public int changeUserBlockedStatus(@PathVariable int id, @PathVariable boolean isBlocked) {
        return userService.changeUserBlockedStatus(id, isBlocked);
    }
}
