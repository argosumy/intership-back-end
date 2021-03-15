package com.spd.baraholka.user.services;

import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.User;
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

    public int updateUserMainInfo(UserDTO userDTO) {
        User user = userMapper.convertToEntity(userDTO);
        return persistenceUserService.updateUserMainInfo(user);
    }
}
