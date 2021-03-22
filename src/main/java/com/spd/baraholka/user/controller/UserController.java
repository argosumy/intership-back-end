package com.spd.baraholka.user.controller;

import com.spd.baraholka.login.CurrentUser;
import com.spd.baraholka.login.UserPrincipal;
import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.login.service.GoogleOAuth2UserService;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    private final GoogleOAuth2UserService oAuth2UserService;

    public UserController(UserService userService, GoogleOAuth2UserService oAuth2UserService) {
        this.userService = userService;
        this.oAuth2UserService = oAuth2UserService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/me2")
//    @PreAuthorize("hasRole('MODERATOR')")
    public UserPrincipal getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        Objects.requireNonNull(userPrincipal);
        return userPrincipal;
//        return userService.findByEmail(userPrincipal.getEmail());
    }

//    @GetMapping("/me")
//    @PreAuthorize("hasRole('USER')")
//    public User getCurrentUser(Principal principal) {
//        Objects.requireNonNull(principal);
//        return userService.findByEmail(principal.getEmail());
//    }

//    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/me")
    public ResponseEntity<UserDetails> showMe(Authentication authentication) {
        OAuth2UserDTO oAuth2UserDTO = Objects.requireNonNull(oAuth2UserService.getOAuth2UserDTO(authentication));
        String email = oAuth2UserDTO.getEmail();
        UserDetails userDetails = userService.loadUserByUsername(email);
        return ResponseEntity.ok().body(userDetails);
    }
}
