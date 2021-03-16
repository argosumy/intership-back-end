package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.controller.mappers.UserMapper;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PersistenceUserService persistenceUserService;
    private final UserMapper userMapper;

    public UserService(PersistenceUserService persistenceUserService, UserMapper userMapper) {
        this.persistenceUserService = persistenceUserService;
        this.userMapper = userMapper;
    }

    public UserDTO getUserById(int id) {
        User user = persistenceUserService.getUserById(id);
        return userMapper.convertToDTO(user);
    }
}
