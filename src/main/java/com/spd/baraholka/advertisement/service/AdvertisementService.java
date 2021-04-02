package com.spd.baraholka.advertisement.service;

import com.spd.baraholka.advertisement.controller.dto.EditedAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.FullAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.InitialAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.mappers.AdvertisementMapper;
import com.spd.baraholka.advertisement.persistance.PersistenceAdvertisementService;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.config.exceptions.NotFoundByIdException;
import com.spd.baraholka.notification.dto.NotificationDto;
import com.spd.baraholka.notification.service.NotificationService;
import com.spd.baraholka.user.controller.dto.OwnerDTO;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.service.OwnerService;
import com.spd.baraholka.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdvertisementService {

    private final PersistenceAdvertisementService persistenceAdvertisementService;
    private final AdvertisementMapper advertisementMapper;
    private final OwnerService ownerService;
    private final UserService userService;
    private final NotificationService notificationService;

    public AdvertisementService(PersistenceAdvertisementService persistenceAdvertisementService,
                                AdvertisementMapper advertisementMapper,
                                OwnerService ownerService, UserService userService, NotificationService notificationService) {
        this.persistenceAdvertisementService = persistenceAdvertisementService;
        this.advertisementMapper = advertisementMapper;
        this.ownerService = ownerService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public int saveAdvertisement(InitialAdvertisementDTO advertisementDTO) {
        Advertisement advertisement = advertisementMapper.convertToEntity(advertisementDTO);
        return persistenceAdvertisementService.insertAdvertisement(advertisement);
    }

    public int updateAdvertisement(EditedAdvertisementDTO advertisementDTO) {
        Advertisement advertisement = advertisementMapper.convertToEntity(advertisementDTO);
        return persistenceAdvertisementService.updateAdvertisement(advertisement);
    }

    public int updateAdvertisementStatus(int id, AdvertisementStatus status) {
        return persistenceAdvertisementService.updateAdvertisementStatus(id, status);
    }

    public boolean isAdvertisementExist(int id) {
        Optional<Boolean> exist = persistenceAdvertisementService.isExist(id);
        return exist.orElse(false);
    }

    public FullAdvertisementDTO getAdvertisementById(int id) {
        Optional<Advertisement> advertisement = persistenceAdvertisementService.selectAdvertisementById(id);
        if (advertisement.isPresent()) {
            return collectFullAdvertisementDTO(advertisement.get());
        } else {
            throw new NotFoundByIdException(id);
        }
    }

    private FullAdvertisementDTO collectFullAdvertisementDTO(Advertisement advertisement) {
        FullAdvertisementDTO advertisementDTO = advertisementMapper.convertToDTO(advertisement);
        OwnerDTO owner = ownerService.getOwner(advertisement.getOwnerId());
        advertisementDTO.setAdvertisementOwner(owner);
        return advertisementDTO;
    }

    public void sendAllUsersNotification(InitialAdvertisementDTO advertisementDTO) {
        NotificationDto notificationDto = new NotificationDto();
        userService.getAllUsers().stream()
                .map(UserShortViewDTO::getId)
                .peek(notificationDto::setUserMailToId)
                .forEach(notificationService.sendMessage(notificationDto));

    }
}
