package com.spd.baraholka.login.controller;

import com.spd.baraholka.login.dto.OAuth2UserDto;
import com.spd.baraholka.login.service.OAuth2UserService;
import com.spd.baraholka.user.User;
import com.spd.baraholka.user.UserMapper;
import com.spd.baraholka.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component("OAuth2SuccessHandler")
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public static final String DOMAIN_NOT_ALLOWED = "Domain is not allowed for login.";

    private final UserService userService;

    private final UserMapper userMapper;

    @Value("${login.allowed-domains}")
    private List<String> allowedDomains;

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
        if (!isEmailDomainInAllowedDomains(oAuth2UserDto.getEmail(), allowedDomains)) {
            throw new BadCredentialsException(DOMAIN_NOT_ALLOWED);
        }
        if (!userService.existsByEmail(oAuth2UserDto.getEmail())) {
            User user = userMapper.convertToEntity(oAuth2UserDto);
            userService.create(user);
        }
        response.sendRedirect("/me/oauth2");
    }

    private boolean isEmailDomainInAllowedDomains(String email, List<String> allowedDomains) {
        boolean isDomainAllowed = false;
        Objects.requireNonNull(allowedDomains);
        for (String domain : allowedDomains) {
            isDomainAllowed = isDomainAllowed || email.endsWith(domain);
        }
        return isDomainAllowed;
    }
}