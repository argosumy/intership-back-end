package com.spd.baraholka.advertisements.services;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.user.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdvertisementUserEmailMapper {

    private final UserService userService;

    public AdvertisementUserEmailMapper(UserService userService) {
        this.userService = userService;
    }

    public AdvertisementUserEmailDTO getAdvertisementUserEmailDto(Advertisement advertisement) {
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
        return advertisementUserEmailDTO;
    }

    public List<AdvertisementUserEmailDTO> toAdvertisementUserEmailDtoList(List<Advertisement> advertisementList) {
        return advertisementList.stream()
                .map(this::getAdvertisementUserEmailDto)
                .collect(Collectors.toList());
    }

    private String getUserEmail(Advertisement advertisement) {
        return userService.getUserById(advertisement.getOwnerId()).getEmail();
    }
}
