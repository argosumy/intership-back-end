package com.spd.baraholka.login.controller;

import com.spd.baraholka.login.dto.OAuth2UserDto;
import com.spd.baraholka.login.service.OAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {

    private final OAuth2UserService oAuth2UserService;

    public static final String ENDPOINT_ME_OAUTH = "/me/oauth2";

    @Autowired
    public OAuth2Controller(OAuth2UserService oAuth2UserService) {
        this.oAuth2UserService = oAuth2UserService;
    }

    @GetMapping(ENDPOINT_ME_OAUTH)
    public ResponseEntity<OAuth2UserDto> showOAuth2User() {
        OAuth2UserDto oAuth2UserDto = oAuth2UserService.getUserInfoFromOAuth2();
        return ResponseEntity.ok().body(oAuth2UserDto);
    }
}
