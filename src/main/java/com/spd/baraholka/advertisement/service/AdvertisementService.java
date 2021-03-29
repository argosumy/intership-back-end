package com.spd.baraholka.advertisement.service;

import com.spd.baraholka.advertisement.controller.mappers.AdvertisementMapper;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.persistance.PersistenceAdvertisementService;
import com.spd.baraholka.config.exceptions.NotFoundException;
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


    public List<Advertisement> getFilteredAdsByKeyword(String keyword, Integer size) {
        List<Advertisement> advertisementList = getAllActive();
        return advertisementList.stream()
                .filter(ad -> (ad.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        ad.getDescription().toLowerCase().contains(keyword.toLowerCase())))
                .limit(size != null ? size : advertisementList.size())
                .collect(Collectors.toList());
    }

    public List<Advertisement> getAllActive() {
        List<Advertisement> active = persistenceAdvertisementService.getAllActive();
        logger.info("IN getAllActive - advertisements found: {}", active.size());

        setActiveStatus(active);
        return active;
    }

    private void setActiveStatus(List<Advertisement> active) {
        active.forEach(ad -> {
            if (ad.getStatus() == (AdvertisementStatus.DRAFT)) {
                ad.setStatus(AdvertisementStatus.ACTIVE);
                updateAdvertisement(advertisementMapper.getAdvertisementDto(ad));
            }
        });
    }

    public Optional<Advertisement> findDraftAdById(int id) {
        Optional<Advertisement> result = persistenceAdvertisementService.findDraftAdById(id);

        if (result.isEmpty()) {
            logger.warn("IN findDraftAdById - no advertisement found by id: {}", id);
        }
        logger.info("IN findDraftAdById - advertisement: {} found by id: {}", result, id);
        return result;
    }

    public int editPublicationDate(int id, String publicationDate) {
        Advertisement advertisement = findDraftAdById(id)
                .orElseThrow(NotFoundException::new);

        advertisement.setPublicationDate(LocalDateTime.parse(publicationDate));
        return persistenceAdvertisementService.updateAdvertisement(advertisement);
    }
}
