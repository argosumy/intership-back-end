package com.spd.baraholka.user.controller;

import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }
}
