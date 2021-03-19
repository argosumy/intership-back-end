package com.spd.baraholka.user.controller.mappers;

import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserShortViewDTO> convertToDTOList(List<User> users) {
        return users.stream().map(this::convertToShortViewDTO).collect(Collectors.toList());
    }

    private UserShortViewDTO convertToShortViewDTO(User user) {
        UserShortViewDTO userDTO = new UserShortViewDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBlocked(user.isBlocked());
        userDTO.setEndDateOfBan(user.getEndDateOfBan());
        return userDTO;
    }

}
