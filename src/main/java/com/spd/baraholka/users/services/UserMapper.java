package com.spd.baraholka.users.services;

import com.spd.baraholka.users.persistance.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPosition(userDTO.getPosition());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setBlocked(userDTO.isBlocked());
        user.setAdditionalContactResources(userDTO.getAdditionalContactResources());
        return user;
    }
}
