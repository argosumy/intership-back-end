package com.spd.baraholka.user;

public interface UserRepository {

    void create(User user);

    boolean existsByEmail(String email);

    int countAll();
}
