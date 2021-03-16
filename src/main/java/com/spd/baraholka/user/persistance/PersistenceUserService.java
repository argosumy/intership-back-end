package com.spd.baraholka.user.persistance;

public interface PersistenceUserService {

    User getUserById(int id);

    int changeUserBlockedStatus(int id, boolean isBlocked);
}
