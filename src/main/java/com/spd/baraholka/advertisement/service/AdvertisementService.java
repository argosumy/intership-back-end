package com.spd.baraholka.advertisement.service;

import com.spd.baraholka.advertisement.controller.dto.EditedAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.FullAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.InitialAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.mappers.AdvertisementMapper;
import com.spd.baraholka.advertisement.persistance.PersistenceAdvertisementService;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.config.exceptions.NotFoundByIdException;
import com.spd.baraholka.user.controller.dto.OwnerDTO;
import com.spd.baraholka.user.service.OwnerService;
import com.spd.baraholka.characteristic.persistance.CharacteristicService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdvertisementService {

    private final PersistenceAdvertisementService persistenceAdvertisementService;
    private final AdvertisementMapper advertisementMapper;
    private final OwnerService ownerService;
    private final CharacteristicService characteristicService;

    public AdvertisementService(PersistenceAdvertisementService persistenceAdvertisementService,
                                CharacteristicService characteristicService,
                                AdvertisementMapper advertisementMapper,
                                OwnerService ownerService) {
        this.persistenceAdvertisementService = persistenceAdvertisementService;
        this.advertisementMapper = advertisementMapper;
        this.ownerService = ownerService;
        this.characteristicService = characteristicService;
    }

    public int saveAdvertisement(InitialAdvertisementDTO advertisementDTO) {
        Advertisement advertisement = advertisementMapper.convertToEntity(advertisementDTO);
        int adId = persistenceAdvertisementService.insertAdvertisement(advertisement);
        advertisementDTO.getCharacteristics().forEach(characteristicDTO -> characteristicService.save(adId, characteristicDTO));

        return adId;
    }

    public int updateAdvertisement(EditedAdvertisementDTO advertisementDTO) {
        Advertisement advertisement = advertisementMapper.convertToEntity(advertisementDTO);
        characteristicService.update(advertisementDTO.getAdvertisementId(), advertisementDTO.getCharacteristics());

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
}
