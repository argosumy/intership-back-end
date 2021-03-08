package com.spduniversity.controller;

import com.spduniversity.model.OAuth2UserDto;
import com.spduniversity.service.OAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final OAuth2UserService oAuth2UserService;

    // TODO: Remove this temporary endpoint after QA have tested Google OAuth 2.0 authentication
    public static final String ENDPOINT_ME_OAUTH = "/";

    @Autowired
    public UserController(OAuth2UserService oAuth2UserService) {
        this.oAuth2UserService = oAuth2UserService;
    }

    // TODO: Remove this temporary endpoint after QA have tested Google OAuth 2.0 authentication
    @GetMapping(ENDPOINT_ME_OAUTH)
    public ResponseEntity<OAuth2UserDto> showOAuth2User() {
        OAuth2UserDto oAuth2UserDto = oAuth2UserService.getUserInfoFromOAuth2();
        return ResponseEntity.ok().body(oAuth2UserDto);
    }
}
