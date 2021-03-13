package com.spd.baraholka.user.service;

import com.spd.baraholka.login.dto.OAuth2UserDto;
import com.spd.baraholka.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User convertToEntity(OAuth2UserDto oAuth2UserDto) {
        User user = new User();
        user.setFirstName(oAuth2UserDto.getFirstName());
        user.setLastName(oAuth2UserDto.getLastName());
        user.setEmail(oAuth2UserDto.getEmail());
        user.setAvatar(oAuth2UserDto.getAvatar());
        user.setLocation("");
        user.setPosition("");
        user.setPhoneNumber("");
        return user;
    }
}
