package com.spd.baraholka.user.controller.mappers;

import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    //TODO delete this ock when images will be implemented 'BE-ACM-003'
    private static final String IMAGE_URL = "https://baraholka-images-store.s3-eu-west-1.amazonaws.com/users/1/homer3.jpg";

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPosition(user.getPosition());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setBlocked(user.isBlocked());
        userDTO.setImageUrl(IMAGE_URL);
        return userDTO;
    }
}
