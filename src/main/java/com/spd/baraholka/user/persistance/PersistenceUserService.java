package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.persistance.entities.User;

import java.util.List;

public interface PersistenceUserService {

    User selectUserById(int id);

    List<User> selectAllUsers();
}
