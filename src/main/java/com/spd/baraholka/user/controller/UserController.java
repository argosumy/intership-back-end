package com.spd.baraholka.user.controller;

import com.spd.baraholka.login.CurrentUser;
//import com.spd.baraholka.login.UserPrincipal;
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

import java.security.Principal;
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

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/me_currentuser")
    public ResponseEntity<UserPrincipal> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        Objects.requireNonNull(userPrincipal);
        return ResponseEntity.ok().body(userPrincipal);
//        return userService.findByEmail(userPrincipal.getEmail());
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/me_principal")
    public ResponseEntity<Principal> getCurrentUser(Principal principal) {
        Objects.requireNonNull(principal);
        return ResponseEntity.ok().body(principal);
//        return userService.findByEmail(userPrincipal.getEmail());
    }

//    @GetMapping("/me")
//    @PreAuthorize("hasRole('USER')")
//    public User getCurrentUser(Principal principal) {
//        Objects.requireNonNull(principal);
//        return userService.findByEmail(principal.getEmail());
//    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/me_userdetails")
    public ResponseEntity<UserDetails> showMe(@CurrentUser UserPrincipal userPrincipal) {
        UserDetails userDetails = userService.loadUserByUsername(userPrincipal.getUsername());
        return ResponseEntity.ok().body(userDetails);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/me_userservice")
    public ResponseEntity<User> showMe() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok().body(user);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/me_authentication")
    public ResponseEntity<Authentication> showMe2(Authentication authentication) {
        return ResponseEntity.ok().body(authentication);
    }
}
