package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.dto.UserAdditionalResourceDTO;
import com.spd.baraholka.user.controller.mappers.UserAdditionalResourceMapper;
import com.spd.baraholka.user.persistance.PersistenceUserAdditionalResourcesService;
import com.spd.baraholka.user.persistance.entities.UserAdditionalResource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserAdditionalResourceService {

    private final PersistenceUserAdditionalResourcesService persistenceResourceService;
    private final UserAdditionalResourceMapper resourceMapper;
    private final Predicate<UserAdditionalResource> isResourceNew = additionalResourceDTO -> additionalResourceDTO.getId() == 0;

    public UserAdditionalResourceService(PersistenceUserAdditionalResourcesService persistenceResourceService,
                                         UserAdditionalResourceMapper resourceMapper) {
        this.persistenceResourceService = persistenceResourceService;
        this.resourceMapper = resourceMapper;
    }

    public List<UserAdditionalResourceDTO> getUserAdditionalResources(int userId) {
        List<UserAdditionalResource> additionalResources = persistenceResourceService.selectUserAdditionalResources(userId);
        return resourceMapper.convertToDTOList(additionalResources);
    }

    public Set<Integer> getUserAdditionalResourcesId(int userId) {
        List<Integer> resourcesId = persistenceResourceService.selectUserAdditionalResourcesId(userId);
        return new HashSet<>(resourcesId);
    }

    public List<UserAdditionalResourceDTO> updateResourceInfoPart(List<UserAdditionalResourceDTO> resourceDTO, int userId) {
        List<UserAdditionalResource> allResources = resourceMapper.convertToEntityList(resourceDTO, userId);
        Map<Boolean, List<UserAdditionalResource>> dividedResources = divideByExist(allResources);
        persistenceResourceService.updateUserAdditionalResources(dividedResources.getOrDefault(false, Collections.emptyList()));
        persistenceResourceService.insertNewUserAdditionalResources(dividedResources.getOrDefault(true, Collections.emptyList()));
        return collectUserAdditionalResourcesDTO(userId);
    }

    private List<UserAdditionalResourceDTO> collectUserAdditionalResourcesDTO(int userId) {
        List<UserAdditionalResource> additionalResources = persistenceResourceService.selectUserAdditionalResources(userId);
        return resourceMapper.convertToDTOList(additionalResources);
    }

    private Map<Boolean, List<UserAdditionalResource>> divideByExist(List<UserAdditionalResource> resources) {
        return resources.stream().collect(Collectors.groupingBy(isResourceNew::test, Collectors.toList()));
    }
}
