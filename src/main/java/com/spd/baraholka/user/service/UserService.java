package com.spd.baraholka.user.service;

import com.spd.baraholka.user.User;
import com.spd.baraholka.user.persistence.UserPersistence;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserPersistence repository;

    public UserService(UserPersistence repository) {
        this.repository = repository;
    }

    public void create(User user) {
        repository.create(user);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
