package com.spduniversity.service.impl;

import com.spduniversity.model.OAuth2UserDto;
import com.spduniversity.service.OAuth2UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Google OAuth2")
public class GoogleOAuth2UserServiceImpl implements OAuth2UserService {

    private OAuth2User getCurrentOAuth2Principal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (OAuth2User) auth.getPrincipal();
    }

    @Override
    public OAuth2UserDto getUserInfoFromOAuth2() {
        OAuth2User oAuth2User = getCurrentOAuth2Principal();
        String email = oAuth2User.getAttributes().get("email").toString();
        String firstName = oAuth2User.getAttributes().get("given_name").toString();
        String lastName = oAuth2User.getAttributes().get("family_name").toString();
        String avatar = oAuth2User.getAttributes().get("picture").toString();
        return new OAuth2UserDto(email, firstName, lastName, avatar);
    }
}
