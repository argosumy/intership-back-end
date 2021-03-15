package com.spd.baraholka.user.persistance;

public interface PersistenceUserService {

    User getUserById(int id);

    int updateUserMainInfo(User user);
}
