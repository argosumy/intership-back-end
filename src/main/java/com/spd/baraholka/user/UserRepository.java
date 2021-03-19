package com.spd.baraholka.user;

import com.spd.baraholka.role.Role;

import java.util.Optional;
import java.util.Set;

public interface UserRepository {

    User create(User user);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Set<Role> findRolesByUserId(int id);

    int count();
}
