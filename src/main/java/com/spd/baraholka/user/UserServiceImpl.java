package com.spd.baraholka.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(User user) {
        repository.create(user);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
