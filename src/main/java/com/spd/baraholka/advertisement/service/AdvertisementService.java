package com.spd.baraholka.advertisement.service;

import com.spd.baraholka.advertisement.controller.dto.EditedAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.FullAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.InitialAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.mappers.AdvertisementMapper;
import com.spd.baraholka.advertisement.persistance.PersistenceAdvertisementService;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.config.exceptions.NotFoundByIdException;
import com.spd.baraholka.notification.service.Sender;
import com.spd.baraholka.user.controller.dto.OwnerDTO;
import com.spd.baraholka.user.service.OwnerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdvertisementService {

    private final PersistenceAdvertisementService persistenceAdvertisementService;
    private final AdvertisementMapper advertisementMapper;
    private final OwnerService ownerService;
    private final Sender sender;

    public AdvertisementService(PersistenceAdvertisementService persistenceAdvertisementService,
                                AdvertisementMapper advertisementMapper,
                                OwnerService ownerService, @Lazy Sender sender) {
        this.persistenceAdvertisementService = persistenceAdvertisementService;
        this.advertisementMapper = advertisementMapper;
        this.ownerService = ownerService;
        this.sender = sender;
    }

    public int saveAdvertisement(InitialAdvertisementDTO advertisementDTO) {
        Advertisement advertisement = advertisementMapper.convertToEntity(advertisementDTO);
        int id = persistenceAdvertisementService.insertAdvertisement(advertisement);
        advertisement.setAdvertisementId(id);
//        sender.sendAllUsersNotification(advertisement);
        sender.sendAllUsersNotification(advertisementMapper.convertToDTO(advertisement));
        return id;
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

    //TODO getUserById
    //TODO getAllUsers
}
