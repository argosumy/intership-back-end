package com.spd.baraholka.user.service;

import com.spd.baraholka.user.persistance.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPosition(userDTO.getPosition());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setBlocked(userDTO.isBlocked());
        user.setAdditionalContactResources(userDTO.getAdditionalContactResources());
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
        userDTO.setBlocked(user.isBlocked());
        userDTO.setAdditionalContactResources(user.getAdditionalContactResources());
        return userDTO;
    }
}
