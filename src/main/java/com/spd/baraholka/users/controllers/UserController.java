package com.spd.baraholka.users.controllers;

import com.spd.baraholka.users.services.UserDTO;
import com.spd.baraholka.users.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PutMapping
    public int updateUserMainInfo(@RequestBody @Valid UserDTO userDTO) {
        return userService.updateUserMainInfo(userDTO);
    }
}
