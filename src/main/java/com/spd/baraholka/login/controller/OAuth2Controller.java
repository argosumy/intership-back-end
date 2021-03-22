package com.spd.baraholka.login.controller;

import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.login.service.GoogleOAuth2UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class OAuth2Controller {

    private final GoogleOAuth2UserService oAuth2UserService;

    private final UserDetailsService userDetailsService;

    public static final String ENDPOINT_ME_OAUTH = "/me/oauth2";
    public static final String ENDPOINT_ME = "/me";

    public OAuth2Controller(GoogleOAuth2UserService oAuth2UserService, @Qualifier("UserService") UserDetailsService userDetailsService) {
        this.oAuth2UserService = oAuth2UserService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(ENDPOINT_ME_OAUTH)
    public ResponseEntity<OAuth2UserDTO> showOAuth2User() {
        OAuth2UserDTO oAuth2UserDto = oAuth2UserService.getOAuth2UserDTO();
        return ResponseEntity.ok().body(oAuth2UserDto);
    }

    @GetMapping(ENDPOINT_ME)
    public ResponseEntity<UserDetails> showMe(Authentication authentication) {
        OAuth2UserDTO oAuth2UserDTO = Objects.requireNonNull(oAuth2UserService.getOAuth2UserDTO(authentication));
        String email = oAuth2UserDTO.getEmail();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return ResponseEntity.ok().body(userDetails);
    }
}
