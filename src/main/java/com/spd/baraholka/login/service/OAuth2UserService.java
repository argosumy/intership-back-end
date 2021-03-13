package com.spd.baraholka.login.service;

import com.spd.baraholka.login.dto.OAuth2UserDto;
import org.springframework.security.core.Authentication;

public interface OAuth2UserService {
    OAuth2UserDto getUserInfoFromOAuth2();

    OAuth2UserDto getUserInfoFromOAuth2(Authentication authentication);
}
