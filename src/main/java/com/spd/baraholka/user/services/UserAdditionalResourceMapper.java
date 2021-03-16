package com.spd.baraholka.user.services;

import com.spd.baraholka.user.persistance.UserAdditionalResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAdditionalResourceMapper {

    public List<UserAdditionalResource> convertToEntityList(List<UserAdditionalResourceDTO> additionalResourceDTOS) {
        return additionalResourceDTOS.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    public List<UserAdditionalResourceDTO> convertToDTOList(List<UserAdditionalResource> additionalResources) {
        return additionalResources.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private UserAdditionalResource convertToEntity(UserAdditionalResourceDTO additionalResourceDTO) {
        UserAdditionalResource userAdditionalResource = new UserAdditionalResource();
        userAdditionalResource.setId(additionalResourceDTO.getId());
        userAdditionalResource.setUserId(additionalResourceDTO.getUserId());
        userAdditionalResource.setResourceName(additionalResourceDTO.getResourceName());
        userAdditionalResource.setResourceUrl(additionalResourceDTO.getResourceUrl());
        return userAdditionalResource;
    }

    private UserAdditionalResourceDTO convertToDTO(UserAdditionalResource additionalResource) {
        UserAdditionalResourceDTO additionalResourceDTO = new UserAdditionalResourceDTO();
        additionalResourceDTO.setId(additionalResource.getId());
        additionalResourceDTO.setUserId(additionalResource.getUserId());
        additionalResourceDTO.setResourceName(additionalResource.getResourceName());
        additionalResourceDTO.setResourceUrl(additionalResource.getResourceUrl());
        return additionalResourceDTO;
    }
}
