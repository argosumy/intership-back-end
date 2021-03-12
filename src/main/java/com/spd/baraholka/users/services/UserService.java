package com.spd.baraholka.users.services;

import com.spd.baraholka.users.persistance.PersistenceUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PersistenceUserService persistenceUserService;
    private final UserMapper userMapper;

    public UserService(PersistenceUserService persistenceUserService, UserMapper userMapper) {
        this.persistenceUserService = persistenceUserService;
        this.userMapper = userMapper;
    }
}
