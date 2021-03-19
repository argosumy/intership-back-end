package com.spd.baraholka.user.controller.mappers;

import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPosition(user.getPosition());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setBlocked(user.isBlocked());
        return userDTO;
    }
}
