package com.spd.baraholka.user.service;

import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.user.persistance.entities.User;

public interface UserServiceI {

    void create(User user);

    boolean existsByEmail(String email);

    int count();

    User convertFromOAuth(OAuth2UserDTO oAuth2UserDto);
}
