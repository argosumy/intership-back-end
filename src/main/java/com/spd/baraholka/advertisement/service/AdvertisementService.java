package com.spd.baraholka.advertisement.service;

import com.spd.baraholka.advertisement.persistance.Advertisement;
import com.spd.baraholka.advertisement.persistance.AdvertisementStatus;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {

    private final PersistenceAdvertisementService persistenceAdvertisementService;
    private final AdvertisementMapper advertisementMapper;

    public AdvertisementService(PersistenceAdvertisementService persistenceAdvertisementService,
                                AdvertisementMapper advertisementMapper) {
        this.persistenceAdvertisementService = persistenceAdvertisementService;
        this.advertisementMapper = advertisementMapper;
    }

    public int saveAdvertisement(AdvertisementDTO advertisementDTO) {
        Advertisement advertisement = advertisementMapper.convertToEntity(advertisementDTO);
        return persistenceAdvertisementService.saveAdvertisement(advertisement);
    }

    public int updateAdvertisement(AdvertisementDTO advertisementDTO) {
        Advertisement advertisement = advertisementMapper.convertToEntity(advertisementDTO);
        return persistenceAdvertisementService.updateAdvertisement(advertisement);
    }

    public int updateAdvertisementStatus(int id, AdvertisementStatus status) {
        return persistenceAdvertisementService.updateAdvertisementStatus(id, status);
    }
}
