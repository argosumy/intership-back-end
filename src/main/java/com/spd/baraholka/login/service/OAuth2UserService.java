package com.spd.baraholka.login.service;

import com.spd.baraholka.login.dto.OAuth2UserDto;

public interface OAuth2UserService {
    OAuth2UserDto getUserInfoFromOAuth2();
}
