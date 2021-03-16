package com.spd.baraholka.user.persistance;

public interface PersistenceUserService {

    User selectUserById(int id);

    int updateUserMainInfo(User user);
}
