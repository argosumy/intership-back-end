package com.spd.baraholka.advertisement.controller.mappers;

import com.spd.baraholka.advertisement.controller.dto.AdvertisementUserEmailDTO;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.image.persistance.entity.ImageResource;
import com.spd.baraholka.user.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementUserEmailMapper {

    private final UserService userService;

    public AdvertisementUserEmailMapper(UserService userService) {
        this.userService = userService;
    }

    public AdvertisementUserEmailDTO getAdvertisementUserEmailDto(Advertisement advertisement, ImageResource imageResource) {
        AdvertisementUserEmailDTO advertisementUserEmailDTO = new AdvertisementUserEmailDTO();
        advertisementUserEmailDTO.setAdvertisementId(advertisement.getAdvertisementId());
        advertisementUserEmailDTO.setOwnerId(advertisement.getOwnerId());
        advertisementUserEmailDTO.setTitle(advertisement.getTitle());
        advertisementUserEmailDTO.setDescription(advertisement.getDescription());
        advertisementUserEmailDTO.setCategory(advertisement.getCategory());
        advertisementUserEmailDTO.setPrice(advertisement.getPrice());
        advertisementUserEmailDTO.setCurrency(advertisement.getCurrency());
        advertisementUserEmailDTO.setDiscountAvailability(advertisement.isDiscountAvailability());
        advertisementUserEmailDTO.setCity(advertisement.getCity());
        advertisementUserEmailDTO.setStatus(advertisement.getStatus());
        advertisementUserEmailDTO.setCreationDate(advertisement.getCreationDate());
        advertisementUserEmailDTO.setPublicationDate(advertisement.getPublicationDate());
        advertisementUserEmailDTO.setStatusChangeDate(advertisement.getStatusChangeDate());
        advertisementUserEmailDTO.setUserEmail(getUserEmail(advertisement));
        advertisementUserEmailDTO.setImagePath(imageResource.getImageUrl());
        return advertisementUserEmailDTO;
    }

    private String getUserEmail(Advertisement advertisement) {
        return userService.getUserById(advertisement.getOwnerId()).getEmail();
    }
}
