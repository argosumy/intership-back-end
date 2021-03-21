package com.spd.baraholka.login.controller;

import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.login.service.OAuth2UserService;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.service.UserService;
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

import static com.spd.baraholka.role.Role.MODERATOR;

@Component("OAuth2SuccessHandler")
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public static final String DOMAIN_NOT_ALLOWED = "Domain is not allowed for login.";

    private final UserService userService;

    @Value("${login.allowed-domains}")
    private List<String> allowedDomains;

    @Qualifier("Google OAuth2")
    private final OAuth2UserService oAuth2UserService;

    public OAuth2AuthenticationSuccessHandler(UserService userService, OAuth2UserService oAuth2UserService) {
        this.userService = userService;
        this.oAuth2UserService = oAuth2UserService;
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2UserDTO oAuth2UserDto = Objects.requireNonNull(oAuth2UserService.getUserInfoFromOAuth2(authentication));
        if (!isEmailDomainInAllowedDomains(oAuth2UserDto.getEmail(), allowedDomains)) {
            throw new BadCredentialsException(DOMAIN_NOT_ALLOWED);
        }
        if (!userService.existsByEmail(oAuth2UserDto.getEmail())) {
            User user = userService.convertFromOAuth(oAuth2UserDto);
            if (userService.count() == 0) {
                user.grantRole(MODERATOR);
            }
            userService.create(user);
        }
        response.sendRedirect("/");
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