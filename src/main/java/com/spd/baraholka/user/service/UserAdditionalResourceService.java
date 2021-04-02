package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.dto.UserAdditionalResourceDTO;
import com.spd.baraholka.user.controller.mappers.UserAdditionalResourceMapper;
import com.spd.baraholka.user.persistance.PersistenceUserAdditionalResourcesService;
import com.spd.baraholka.user.persistance.entities.UserAdditionalResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdditionalResourceService {

    private final PersistenceUserAdditionalResourcesService persistenceResourceService;
    private final UserAdditionalResourceMapper resourceMapper;

    public UserAdditionalResourceService(PersistenceUserAdditionalResourcesService persistenceResourceService,
                                         UserAdditionalResourceMapper resourceMapper) {
        this.persistenceResourceService = persistenceResourceService;
        this.resourceMapper = resourceMapper;
    }

    public List<UserAdditionalResourceDTO> getUserAdditionalResources(int userId) {
        List<UserAdditionalResource> additionalResources = persistenceResourceService.selectUserAdditionalResources(userId);
        return resourceMapper.convertToDTOList(additionalResources);
    }
}
