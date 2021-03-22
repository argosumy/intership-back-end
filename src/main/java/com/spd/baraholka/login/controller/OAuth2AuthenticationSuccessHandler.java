package com.spd.baraholka.login.controller;

import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.login.service.GoogleOAuth2UserService;
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

import static com.spd.baraholka.role.Role.ROLE_MODERATOR;

@Component("OAuth2SuccessHandler")
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {



    private final UserService userService;

    private final GoogleOAuth2UserService oAuth2UserService;

    public OAuth2AuthenticationSuccessHandler(UserService userService, GoogleOAuth2UserService oAuth2UserService) {
        this.userService = userService;
        this.oAuth2UserService = oAuth2UserService;
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

//        oAuth2UserService.loadUser();
        response.sendRedirect("/users/me");
//        OAuth2UserDTO oAuth2UserDto = Objects.requireNonNull(oAuth2UserService.getOAuth2UserDTO(authentication));


//        userService.registerNewUser(oAuth2UserDto);



//        if (!isEmailDomainInAllowedDomains(oAuth2UserDto.getEmail(), allowedDomains)) {
//            throw new BadCredentialsException(DOMAIN_NOT_ALLOWED);
//        }
//        if (!userService.existsByEmail(oAuth2UserDto.getEmail())) {
//            User user = userService.convertFromOAuth(oAuth2UserDto);
//            if (userService.count() == 0) {
//                user.grantRole(MODERATOR);
//            }
//            userService.create(user);
//        }
//        response.sendRedirect("/");
    }
}