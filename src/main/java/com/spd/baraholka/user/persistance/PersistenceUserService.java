package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.persistance.entities.User;

import java.util.Optional;

public interface PersistenceUserService {

    User create(User user);

    Optional<User> selectUserById(int id);

    boolean existsByEmail(String email);

    int count();

    int updateUserMainInfo(User user);
}
