package com.spd.baraholka.advertisements.services;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.advertisements.persistance.AdvertisementStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertisementService {

    private final PersistenceAdvertisementService persistenceAdvertisementService;
    private final AdvertisementMapper advertisementMapper;
    private static final Logger logger = LoggerFactory.getLogger(AdvertisementService.class);

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

    public List<Advertisement> getFilteredAdsByTitle(String title) {
        return getAllActive().stream()
                .filter(ad -> ad.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    public List<Advertisement> getFilteredAdsByDescription(String description) {
        return persistenceAdvertisementService.findAdsByDescription(description);
    }

    public List<Advertisement> getAllActive() {
        return persistenceAdvertisementService.getAllActive();
    }

    public Optional<Advertisement> findDraftAdById(int id) {
        Optional<Advertisement> result = persistenceAdvertisementService.findDraftAdById(id);

        if (result.isEmpty()) {
            logger.warn("IN findDraftAdById - no advertisement found by id: {}", id);
        }
        logger.info("IN findDraftAdById - advertisement: {} found by id: {}", result, id);
        return result;
    }

    public void editPublicationDate(Advertisement advertisement, String publicationDate) {
        advertisement.setPublicationDate(LocalDateTime.parse(publicationDate));
    }
}
