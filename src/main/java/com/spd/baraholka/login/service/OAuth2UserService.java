package com.spd.baraholka.login.service;

import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import org.springframework.security.core.Authentication;

public interface OAuth2UserService {
    OAuth2UserDTO getUserInfoFromOAuth2();

    OAuth2UserDTO getUserInfoFromOAuth2(Authentication authentication);
}
