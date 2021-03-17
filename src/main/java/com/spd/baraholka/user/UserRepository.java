package com.spd.baraholka.user;

public interface UserRepository {

    User create(User user);

    boolean existsByEmail(String email);

    int count();
}
