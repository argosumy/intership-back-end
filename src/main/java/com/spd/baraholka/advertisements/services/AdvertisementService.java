package com.spd.baraholka.advertisements.services;

import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {

    private final PersistenceAdvertisementService persistenceAdvertisementService;

    public AdvertisementService(PersistenceAdvertisementService persistenceAdvertisementService) {
        this.persistenceAdvertisementService = persistenceAdvertisementService;
    }
}
