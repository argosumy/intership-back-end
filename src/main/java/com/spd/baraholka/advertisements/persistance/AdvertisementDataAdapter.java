package com.spd.baraholka.advertisements.persistance;

import com.spd.baraholka.advertisements.service.PersistenceAdvertisementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class AdvertisementDataAdapter implements PersistenceAdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    AdvertisementDataAdapter(AdvertisementRepository advertisementRepository) {
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

    @Override
    public List<Advertisement> getAllActive() {
        return advertisementRepository.getAllActive();
    }

    @Override
    public Optional<Advertisement> findDraftAdById(int id) {
        return advertisementRepository.findDraftAdById(id);
    }
}
