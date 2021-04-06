package com.spd.baraholka.login.controller;

import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {

    private final UserProfileService userService;

    public static final String ENDPOINT_ME = "/me";

    public OAuth2Controller(UserProfileService userService) {
        this.userService = userService;
    }

    @GetMapping(ENDPOINT_ME)
    public ResponseEntity<UserDTO> showMe() {
        UserDTO userDTO = userService.getCurrentUser();
        return ResponseEntity.ok().body(userDTO);
    }
}
