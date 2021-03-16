package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.persistance.entities.User;

public interface PersistenceUserService {

    User getUserById(int id);

    int changeUserBlockedStatus(int id, boolean isBlocked);
}
