package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.persistance.entities.User;

public interface PersistenceUserService {

    User selectUserById(int id);

    int updateUserMainInfo(User user);
}
