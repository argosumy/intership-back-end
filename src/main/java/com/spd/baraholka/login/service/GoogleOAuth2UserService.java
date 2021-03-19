package com.spd.baraholka.login.service;

import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Google OAuth2")
public class GoogleOAuth2UserService implements OAuth2UserService {

    public static final String EMAIL_CLAIM_ATTRIBUTE = "email";
    public static final String FIRST_NAME_CLAIM_ATTRIBUTE = "given_name";
    public static final String LAST_NAME_CLAIM_ATTRIBUTE = "family_name";
    public static final String AVATAR_CLAIM_ATTRIBUTE = "picture";

    @Override
    public OAuth2UserDTO getUserInfoFromOAuth2() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getUserInfoFromOAuth2(authentication);
    }

    @Override
    public OAuth2UserDTO getUserInfoFromOAuth2(Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttributes().get(EMAIL_CLAIM_ATTRIBUTE).toString();
        String firstName = oAuth2User.getAttributes().get(FIRST_NAME_CLAIM_ATTRIBUTE).toString();
        String lastName = oAuth2User.getAttributes().get(LAST_NAME_CLAIM_ATTRIBUTE).toString();
        String avatar = oAuth2User.getAttributes().get(AVATAR_CLAIM_ATTRIBUTE).toString();
        return new OAuth2UserDTO(email, firstName, lastName, avatar);
    }
}
