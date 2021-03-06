package com.spd.baraholka.user.controller.mappers;

import com.spd.baraholka.user.controller.dto.OwnerDTO;
import com.spd.baraholka.user.persistance.entities.Owner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {

    @Value("${amazonProperties.imagesUrl}")
    private String awsImageUrl;

    public OwnerDTO convertToDTO(Owner owner) {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId(owner.getId());
        ownerDTO.setFullName(owner.getFullName());
        String imageUrl = owner.getImageUrl();
        ownerDTO.setImageUrl(imageUrl);
        ownerDTO.setEmail(owner.getEmail());
        return ownerDTO;
    }
}
