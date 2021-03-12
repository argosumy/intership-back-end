package com.spd.baraholka.users.persistance;

public interface PersistenceUserService {

    User getUserById(int id);

    int updateUserMainInfo(User user);
}
