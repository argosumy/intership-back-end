package com.spd.baraholka.user.controller.mappers;

import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.user.controller.dto.UserProfileDTO;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserProfileDTO convertToDTO(User user) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(user.getId());
        userProfileDTO.setFirstName(user.getFirstName());
        userProfileDTO.setLastName(user.getLastName());
        userProfileDTO.setEmail(user.getEmail());
        userProfileDTO.setPosition(user.getPosition());
        userProfileDTO.setPhoneNumber(user.getPhoneNumber());
        userProfileDTO.setBlocked(user.isBlocked());
        return userProfileDTO;
    }

    public List<UserShortViewDTO> convertToDTOList(List<User> users) {
        return users.stream().map(this::convertToShortViewDTO).collect(Collectors.toList());
    }

    private UserShortViewDTO convertToShortViewDTO(User user) {
        UserShortViewDTO userDTO = new UserShortViewDTO();
        userDTO.setId(user.getId());
        userDTO.setImageUrl(user.getImageUrl());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBlocked(user.isBlocked());
        userDTO.setEndDateOfBan(user.getEndDateOfBan());
        return userDTO;
    }

    public User convertFromOAuth(OAuth2UserDTO oAuth2UserDto) {
        User user = new User();
        user.setFirstName(oAuth2UserDto.getFirstName());
        user.setLastName(oAuth2UserDto.getLastName());
        user.setEmail(oAuth2UserDto.getEmail());
        user.setImageUrl(oAuth2UserDto.getImageUrl());
        user.setLocation("");
        user.setPosition("");
        user.setPhoneNumber("");
        return user;
    }
}