package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.persistance.entities.User;

import java.util.Optional;
import java.util.List;

public interface PersistenceUserService {

    User create(User user);

    User selectUserById(int id);

    Optional<Boolean> existsByEmail(String email);

    Optional<Integer> count();

    int updateUserBlockedStatus(int id, boolean isBlocked);

    List<User> selectAllUsers();

    Optional<Boolean> isExist(int value);
}
