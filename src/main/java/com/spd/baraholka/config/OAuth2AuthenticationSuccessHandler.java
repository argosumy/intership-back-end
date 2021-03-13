package com.spd.baraholka.config;

import com.spd.baraholka.login.dto.OAuth2UserDto;
import com.spd.baraholka.login.service.OAuth2UserService;
import com.spd.baraholka.user.User;
import com.spd.baraholka.user.service.UserMapper;
import com.spd.baraholka.user.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("OAuth2SuccessHandler")
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    private final UserMapper userMapper;

    @Qualifier("Google OAuth2")
    private final OAuth2UserService oAuth2UserService;

    public OAuth2AuthenticationSuccessHandler(UserService userService, UserMapper userMapper, OAuth2UserService oAuth2UserService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.oAuth2UserService = oAuth2UserService;
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2UserDto oAuth2UserDto = oAuth2UserService.getUserInfoFromOAuth2(authentication);
        if (!userService.existsByEmail(oAuth2UserDto.getEmail())) {
            User user = userMapper.convertToEntity(oAuth2UserDto);
            userService.create(user);
        }
        response.sendRedirect("/me/oauth2");
    }
}