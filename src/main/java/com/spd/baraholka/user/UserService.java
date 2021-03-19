package com.spd.baraholka.user;

import com.spd.baraholka.role.Role;

import java.util.Set;

public interface UserService {

    void create(User user);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    int count();

    Set<Role> findRolesByUserId(int id);
}
