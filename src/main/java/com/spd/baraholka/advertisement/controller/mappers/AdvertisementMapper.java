package com.spd.baraholka.advertisement.controller.mappers;

import com.spd.baraholka.advertisement.controller.dto.EditedAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.InitialAdvertisementDTO;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.controller.dto.AdvertisementDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdvertisementMapper {

    public Advertisement convertToEntity(AdvertisementDTO advertisementDTO) {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementId(advertisementDTO.getAdvertisementId());
        advertisement.setOwnerId(advertisementDTO.getOwnerId());
        advertisement.setTitle(advertisementDTO.getTitle());
        advertisement.setDescription(advertisementDTO.getDescription());
        advertisement.setCategory(advertisementDTO.getCategory());
        advertisement.setPrice(advertisementDTO.getPrice());
        advertisement.setCurrency(advertisementDTO.getCurrency());
        advertisement.setDiscountAvailability(advertisementDTO.isDiscountAvailability());
        advertisement.setCity(advertisementDTO.getCity());
        advertisement.setStatus(advertisementDTO.getStatus());
        advertisement.setCreationDate(advertisementDTO.getCreationDate());
        advertisement.setPublicationDate(advertisementDTO.getPublicationDate());
        advertisement.setStatusChangeDate(advertisementDTO.getStatusChangeDate());
        return advertisement;
    }

    public List<AdvertisementDTO> toAdvertisementDtoList(List<Advertisement> advertisementList) {
        return advertisementList.stream()
                .map(this::getAdvertisementDto)
                .collect(Collectors.toList());
    }

    public AdvertisementDTO getAdvertisementDto(Advertisement advertisement) {
        AdvertisementDTO advertisementDTO = new AdvertisementDTO();
        advertisementDTO.setAdvertisementId(advertisement.getAdvertisementId());
        advertisementDTO.setOwnerId(advertisement.getOwnerId());
        advertisementDTO.setTitle(advertisement.getTitle());
        advertisementDTO.setDescription(advertisement.getDescription());
        advertisementDTO.setCategory(advertisement.getCategory());
        advertisementDTO.setPrice(advertisement.getPrice());
        advertisementDTO.setCurrency(advertisement.getCurrency());
        advertisementDTO.setDiscountAvailability(advertisement.isDiscountAvailability());
        advertisementDTO.setCity(advertisement.getCity());
        advertisementDTO.setStatus(advertisement.getStatus());
        advertisementDTO.setCreationDate(advertisement.getCreationDate());
        advertisementDTO.setPublicationDate(advertisement.getPublicationDate());
        advertisementDTO.setStatusChangeDate(advertisement.getStatusChangeDate());
        return advertisementDTO;
    }

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

    public EditedAdvertisementDTO convertToEditedAdvertisementDTO(Advertisement advertisement) {
        EditedAdvertisementDTO editedAdvertisementDTO = new EditedAdvertisementDTO();
        editedAdvertisementDTO.setAdvertisementId(advertisement.getAdvertisementId());
        editedAdvertisementDTO.setTitle(advertisement.getTitle());
        editedAdvertisementDTO.setDescription(advertisement.getDescription());
        editedAdvertisementDTO.setPrice(advertisement.getPrice());
        editedAdvertisementDTO.setCurrency(advertisement.getCurrency());
        editedAdvertisementDTO.setDiscountAvailability(advertisement.isDiscountAvailability());
        editedAdvertisementDTO.setCity(advertisement.getCity());
        editedAdvertisementDTO.setStatus(advertisement.getStatus());
        editedAdvertisementDTO.setPublicationDate(advertisement.getPublicationDate());
        return editedAdvertisementDTO;
    }
}
