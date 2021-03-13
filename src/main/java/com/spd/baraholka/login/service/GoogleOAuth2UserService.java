package com.spd.baraholka.login.service;

import com.spd.baraholka.login.dto.OAuth2UserDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Google OAuth2")
public class GoogleOAuth2UserService implements OAuth2UserService {

    @Override
    public OAuth2UserDto getUserInfoFromOAuth2() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getUserInfoFromOAuth2(authentication);
    }

    @Override
    public OAuth2UserDto getUserInfoFromOAuth2(Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttributes().get("email").toString();
        String firstName = oAuth2User.getAttributes().get("given_name").toString();
        String lastName = oAuth2User.getAttributes().get("family_name").toString();
        String avatar = oAuth2User.getAttributes().get("picture").toString();
        return new OAuth2UserDto(email, firstName, lastName, avatar);
    }
}
