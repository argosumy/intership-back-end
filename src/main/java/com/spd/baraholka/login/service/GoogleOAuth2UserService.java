package com.spd.baraholka.login.service;

import com.spd.baraholka.config.exceptions.OAuth2ProcessingException;
import com.spd.baraholka.login.UserPrincipal;
import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
//import com.spd.baraholka.login.UserPrincipal;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Qualifier("GoogleOAuth2Service")
public class GoogleOAuth2UserService extends DefaultOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserService userService;

    public static final String EMAIL_CLAIM_ATTRIBUTE = "email";
    public static final String FIRST_NAME_CLAIM_ATTRIBUTE = "given_name";
    public static final String LAST_NAME_CLAIM_ATTRIBUTE = "family_name";
    public static final String AVATAR_CLAIM_ATTRIBUTE = "picture";

    public static final String DOMAIN_NOT_ALLOWED = "Domain is not allowed for login.";

    @Value("${login.allowed-domains}")
    private List<String> allowedDomains;

    public GoogleOAuth2UserService(UserService userService) {
        this.userService = userService;
    }

//    @Override
    public OAuth2UserDTO getOAuth2UserDTO() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getOAuth2UserDTO(authentication);
    }

//    @Override
    public OAuth2UserDTO getOAuth2UserDTO(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttributes().get(EMAIL_CLAIM_ATTRIBUTE).toString();
        String firstName = oAuth2User.getAttributes().get(FIRST_NAME_CLAIM_ATTRIBUTE).toString();
        String lastName = oAuth2User.getAttributes().get(LAST_NAME_CLAIM_ATTRIBUTE).toString();
        String avatar = oAuth2User.getAttributes().get(AVATAR_CLAIM_ATTRIBUTE).toString();
        return new OAuth2UserDTO(email, firstName, lastName, avatar);
    }

//    @Override
    public OAuth2UserDTO getOAuth2UserDTO(Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        return getOAuth2UserDTO(oAuth2User);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        try {
            return processOAuth2User(userRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserDTO oAuth2UserDTO = getOAuth2UserDTO(oAuth2User);
        if (oAuth2UserDTO.getEmail() == null) {
            throw new OAuth2ProcessingException("Email not found from OAuth2 provider");
        }
        User user;
        try {
            user = userService.findByEmail(oAuth2UserDTO.getEmail());
        } catch (UsernameNotFoundException exception) {
            user = registerNewUser(oAuth2UserDTO);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }


    private User registerNewUser(OAuth2UserDTO oAuth2UserDTO) {
        if (!isEmailDomainInAllowedDomains(oAuth2UserDTO.getEmail(), allowedDomains)) {
            throw new OAuth2ProcessingException(DOMAIN_NOT_ALLOWED);
        }
        return userService.registerNewUser(oAuth2UserDTO);
    }

    private boolean isEmailDomainInAllowedDomains(String email, List<String> allowedDomains) {
        boolean isDomainAllowed = false;
        Objects.requireNonNull(allowedDomains);
        for (String domain : allowedDomains) {
            isDomainAllowed = isDomainAllowed || email.endsWith(domain);
        }
        return isDomainAllowed;
    }

//    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserDTO oAuth2UserDTO) {
//        User user = new User();
//
//
//
//        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
//        user.setProviderId(oAuth2UserDTO.getId());
//        user.setName(oAuth2UserDTO.getName());
//        user.setEmail(oAuth2UserDTO.getEmail());
//        user.setImageUrl(oAuth2UserDTO.getImageUrl());
//        return userRepository.save(user);
//    }
}
