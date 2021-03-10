package com.spd.baraholka.advertisements.persistance;

import com.spd.baraholka.advertisements.services.PersistenceAdvertisementService;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementDataAdapter implements PersistenceAdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    public AdvertisementDataAdapter(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public int saveAdvertisement(Advertisement advertisement) {
        return advertisementRepository.addAdvertisement(advertisement);
    }

    @Override
    public int updateAdvertisement(Advertisement advertisement) {
        return advertisementRepository.updateAdvertisement(advertisement);
    }

    @Override
    public int updateAdvertisementStatus(int id, AdvertisementStatus status) {
        return advertisementRepository.updateAdvertisementStatus(id, status);
    }
}
