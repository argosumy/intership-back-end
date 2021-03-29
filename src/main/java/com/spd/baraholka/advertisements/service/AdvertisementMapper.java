package com.spd.baraholka.advertisements.service;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import org.springframework.stereotype.Component;

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
}
