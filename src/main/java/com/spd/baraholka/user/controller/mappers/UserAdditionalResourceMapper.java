package com.spd.baraholka.user.controller.mappers;

import com.spd.baraholka.user.controller.dto.UserAdditionalResourceDTO;
import com.spd.baraholka.user.persistance.entities.UserAdditionalResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAdditionalResourceMapper {

    public List<UserAdditionalResource> convertToEntityList(List<UserAdditionalResourceDTO> additionalResourceDTOS, int userId) {
        return additionalResourceDTOS.stream().map(resource -> convertToEntity(resource, userId)).collect(Collectors.toList());
    }

    public List<UserAdditionalResourceDTO> convertToDTOList(List<UserAdditionalResource> additionalResources) {
        return additionalResources.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private UserAdditionalResource convertToEntity(UserAdditionalResourceDTO additionalResourceDTO, int userId) {
        UserAdditionalResource userAdditionalResource = new UserAdditionalResource();
        userAdditionalResource.setId(additionalResourceDTO.getId());
        userAdditionalResource.setUserId(userId);
        userAdditionalResource.setResourceName(additionalResourceDTO.getResourceName());
        userAdditionalResource.setResourceUrl(additionalResourceDTO.getResourceUrl());
        return userAdditionalResource;
    }

    private UserAdditionalResourceDTO convertToDTO(UserAdditionalResource additionalResource) {
        UserAdditionalResourceDTO additionalResourceDTO = new UserAdditionalResourceDTO();
        additionalResourceDTO.setId(additionalResource.getId());
        additionalResourceDTO.setResourceName(additionalResource.getResourceName());
        additionalResourceDTO.setResourceUrl(additionalResource.getResourceUrl());
        return additionalResourceDTO;
    }
}
