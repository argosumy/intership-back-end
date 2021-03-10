package com.spd.baraholka.advertisements.persistance;

import com.spd.baraholka.advertisements.services.PersistenceAdvertisementService;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementDataAdapter implements PersistenceAdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    public AdvertisementDataAdapter(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }
}
