package com.spd.baraholka.user.controller;

import com.spd.baraholka.annotation.user.UserExist;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") @UserExist int id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserShortViewDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
