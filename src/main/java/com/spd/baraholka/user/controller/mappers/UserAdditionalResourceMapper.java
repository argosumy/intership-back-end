package com.spd.baraholka.user.controller.mappers;

import com.spd.baraholka.user.controller.dto.UserAdditionalResourceDTO;
import com.spd.baraholka.user.persistance.entities.UserAdditionalResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAdditionalResourceMapper {

    public List<UserAdditionalResourceDTO> convertToDTOList(List<UserAdditionalResource> additionalResources) {
        return additionalResources.stream().map(this::convertToDTO).collect(Collectors.toList());
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
