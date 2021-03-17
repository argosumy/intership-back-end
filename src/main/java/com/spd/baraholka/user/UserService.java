package com.spd.baraholka.user;

public interface UserService {

    void create(User user);

    boolean existsByEmail(String email);

    int count();
}
