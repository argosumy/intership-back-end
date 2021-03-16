package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.persistance.entities.UserAdditionalResource;

import java.util.List;

public interface PersistenceUserAdditionalResourcesService {

    List<UserAdditionalResource> selectUserAdditionalResources(int id);
}
