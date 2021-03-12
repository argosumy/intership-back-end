package com.spd.baraholka.users.persistance;

public interface PersistenceUserService {

    User getUserById(int id);

    int changeUserBlockedStatus(int id, boolean isBlocked);
}
