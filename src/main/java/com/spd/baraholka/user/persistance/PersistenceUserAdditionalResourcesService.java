package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.persistance.entities.UserAdditionalResource;

import java.util.List;

public interface PersistenceUserAdditionalResourcesService {

    List<UserAdditionalResource> selectUserAdditionalResources(int id);

    void updateUserAdditionalResources(List<UserAdditionalResource> additionalResources);

    void insertNewUserAdditionalResources(List<UserAdditionalResource> additionalResources);

    List<Integer> selectUserAdditionalResourcesId(int userId);
}
