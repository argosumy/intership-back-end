package com.spd.baraholka.advertisement.controller.mappers;

import com.spd.baraholka.advertisement.controller.dto.EditedAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.FullAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.InitialAdvertisementDTO;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdvertisementMapper {

    public Advertisement convertToEntity(InitialAdvertisementDTO advertisementDTO) {
        Advertisement advertisement = new Advertisement();
        advertisement.setOwnerId(1); //TODO must be replace by user id from cookies session
        advertisement.setCategory(advertisementDTO.getCategory());
        advertisement.setTitle(advertisementDTO.getTitle());
        advertisement.setDescription(advertisementDTO.getDescription());
        advertisement.setPrice(advertisementDTO.getPrice());
        advertisement.setCurrency(advertisementDTO.getCurrency());
        advertisement.setDiscountAvailability(advertisementDTO.isDiscountAvailability());
        advertisement.setCity(advertisementDTO.getCity());
        advertisement.setStatus(advertisementDTO.getStatus());
        advertisement.setCreationDate(LocalDateTime.now());
        advertisement.setPublicationDate(advertisementDTO.getPublicationDate());
        advertisement.setStatusChangeDate(LocalDateTime.now());
        return advertisement;
    }

    public Advertisement convertToEntity(EditedAdvertisementDTO advertisementDTO) {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementId(advertisementDTO.getAdvertisementId());
        advertisement.setOwnerId(1); //TODO must be replace by user id from cookies session
        advertisement.setTitle(advertisementDTO.getTitle());
        advertisement.setDescription(advertisementDTO.getDescription());
        advertisement.setPrice(advertisementDTO.getPrice());
        advertisement.setCurrency(advertisementDTO.getCurrency());
        advertisement.setDiscountAvailability(advertisementDTO.isDiscountAvailability());
        advertisement.setCity(advertisementDTO.getCity());
        advertisement.setStatus(advertisementDTO.getStatus());
        advertisement.setPublicationDate(advertisementDTO.getPublicationDate());
        advertisement.setStatusChangeDate(LocalDateTime.now());
        return advertisement;
    }

    public FullAdvertisementDTO convertToDTO(Advertisement advertisement) {
        FullAdvertisementDTO advertisementDTO = new FullAdvertisementDTO();
        advertisementDTO.setAdvertisementId(advertisement.getAdvertisementId());
        advertisementDTO.setTitle(advertisement.getTitle());
        advertisementDTO.setDescription(advertisement.getDescription());
        advertisementDTO.setPrice(advertisement.getPrice());
        advertisementDTO.setCategory(advertisement.getCategory());
        advertisementDTO.setCurrency(advertisement.getCurrency());
        advertisementDTO.setDiscountAvailability(advertisement.isDiscountAvailability());
        advertisementDTO.setCity(advertisement.getCity());
        advertisementDTO.setStatus(advertisement.getStatus());
        advertisementDTO.setPublicationDate(advertisement.getPublicationDate());
        return advertisementDTO;
    }
}
