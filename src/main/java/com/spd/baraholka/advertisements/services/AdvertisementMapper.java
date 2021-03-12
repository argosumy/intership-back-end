package com.spd.baraholka.advertisements.services;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.comments.dto.CommentDto;
import com.spd.baraholka.comments.entities.Comment;
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

    public List<Advertisement> toAdvertisementDtoList(List<Advertisement> advertisementList) {
        return advertisementList.stream()
                .map(this::getAdvertisementDto)
                .collect(Collectors.toList());
    }

    public AdvertisementDTO getAdvertisementDto(Advertisement advertisement) {
        return new AdvertisementDTO(
                advertisement.getAdvertisementId(),
        advertisement.getOwnerId(),
        advertisement.getTitle(),
        advertisement.getDescription(),
        advertisement.getCategory(),
        advertisement.getPrice(),
        advertisement.getCurrency(),
        advertisement.isDiscountAvailability(),
        advertisement.getCity(),
        advertisement.getStatus(),
        advertisement.getCreationDate(),
        advertisement.getPublicationDate(),
        advertisement.getStatusChangeDate()
        );
    }
}
