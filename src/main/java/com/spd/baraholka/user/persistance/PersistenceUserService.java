package com.spd.baraholka.user.persistance;

import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.persistance.entities.User;

import java.util.Optional;
import java.util.Set;

public interface PersistenceUserService {

    User create(User user);

    Optional<User> selectUserById(int id);

    Optional<Boolean> existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Set<Role> findRolesByUserId(int id);

    Optional<Integer> count();
}
