package com.spd.baraholka.user.persistence;

import com.spd.baraholka.user.User;

public interface UserPersistence {
    void create(User user);
    boolean existsByEmail(String email);
}
