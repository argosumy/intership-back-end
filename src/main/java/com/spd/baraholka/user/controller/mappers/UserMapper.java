package com.spd.baraholka.user.controller.mappers;

import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.user.controller.dto.EditUserMainInfoDTO;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Value("${amazonProperties.imagesUrl}")
    private String awsImageUrl;

    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPosition(userDTO.getPosition());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setBlocked(userDTO.isBlocked());
        user.setLocation(userDTO.getLocation());
        return user;
    }

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPosition(user.getPosition());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        String imageUrl = user.getImageUrl();
        userDTO.setImageUrl(imageUrl);
        userDTO.setEndDateOfBan(user.getEndDateOfBan());
        userDTO.setLocation(user.getLocation());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

    public List<UserShortViewDTO> convertToDTOList(List<User> users) {
        return users.stream().map(this::convertToShortViewDTO).collect(Collectors.toList());
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

    public User convertToEntity(EditUserMainInfoDTO mainInfoDTO) {
        User user = new User();
        user.setId(mainInfoDTO.getUserId());
        user.setPosition(mainInfoDTO.getPosition());
        user.setPhoneNumber(mainInfoDTO.getPhoneNumber());
        user.setLocation(mainInfoDTO.getLocation());
        return user;
    }

    public EditUserMainInfoDTO convertToInfoDTO(User user) {
        EditUserMainInfoDTO mainInfoDTO = new EditUserMainInfoDTO();
        mainInfoDTO.setUserId(user.getId());
        mainInfoDTO.setPosition(user.getPosition());
        mainInfoDTO.setPhoneNumber(user.getPhoneNumber());
        mainInfoDTO.setLocation(user.getLocation());
        return mainInfoDTO;
    }

    private UserShortViewDTO convertToShortViewDTO(User user) {
        UserShortViewDTO userDTO = new UserShortViewDTO();
        userDTO.setId(user.getId());
        String imageUrl = user.getImageUrl();
        userDTO.setImageUrl(imageUrl);
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBlocked(user.isBlocked());
        userDTO.setEndDateOfBan(user.getEndDateOfBan());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    public UserShortViewDTO toShortViewDTO(UserDTO userDTO) {
        UserShortViewDTO userShortViewDTO = new UserShortViewDTO();
        userShortViewDTO.setId(userDTO.getId());
        userShortViewDTO.setFirstName(userDTO.getFirstName());
        userShortViewDTO.setLastName(userDTO.getLastName());
        userShortViewDTO.setBlocked(userDTO.isBlocked());
        userShortViewDTO.setEndDateOfBan(userDTO.getEndDateOfBan());
        userShortViewDTO.setEmail(userDTO.getEmail());
        return userShortViewDTO;
    }

    private String collapseImageUrl(String imageUrl) {
        if (imageUrl.contains("googleusercontent")) { //TODO Delete mock, replace when image saving will be alloy
            return imageUrl;
        } else {
            return awsImageUrl.concat(imageUrl);
        }
    }
}