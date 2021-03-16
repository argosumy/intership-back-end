package com.spd.baraholka.user.persistance;

import java.util.List;

public interface PersistenceUserAdditionalResourcesService {

    List<UserAdditionalResource> selectUserAdditionalResources(int id);

    void updateUserAdditionalResources(List<UserAdditionalResource> additionalResources);
}
