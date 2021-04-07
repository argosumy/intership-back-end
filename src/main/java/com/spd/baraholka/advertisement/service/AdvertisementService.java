package com.spd.baraholka.advertisement.service;

import com.spd.baraholka.advertisement.controller.dto.EditedAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.FilterDTO;
import com.spd.baraholka.advertisement.controller.dto.FullAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.InitialAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.mappers.AdvertisementMapper;
import com.spd.baraholka.advertisement.persistance.PersistenceAdvertisementService;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.characteristic.persistance.CharacteristicService;
import com.spd.baraholka.config.exceptions.BadRequestException;
import com.spd.baraholka.config.exceptions.NotFoundByIdException;
import com.spd.baraholka.config.exceptions.NotFoundException;
import com.spd.baraholka.user.controller.dto.OwnerDTO;
import com.spd.baraholka.user.service.OwnerService;
import com.spd.baraholka.views.service.ViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class AdvertisementService {

    private final PersistenceAdvertisementService persistenceAdvertisementService;
    private final AdvertisementMapper advertisementMapper;
    private static final Logger logger = LoggerFactory.getLogger(AdvertisementService.class);
    private final OwnerService ownerService;
    private final CharacteristicService characteristicService;
    private final ViewService viewService;

    public AdvertisementService(PersistenceAdvertisementService persistenceAdvertisementService,
                                CharacteristicService characteristicService,
                                AdvertisementMapper advertisementMapper,
                                OwnerService ownerService,
                                ViewService viewService) {
        this.persistenceAdvertisementService = persistenceAdvertisementService;
        this.advertisementMapper = advertisementMapper;
        this.ownerService = ownerService;
        this.characteristicService = characteristicService;
        this.viewService = viewService;
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

    @Scheduled(cron = "${baraholka.scheduled.delete-old-archive-task}")
    public void deleteOldArchiveAdvertisements() {
        persistenceAdvertisementService.changeStatusArchivedOnDeleted();
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
        advertisementDTO.setCharacteristics(characteristicService.readForAdId(advertisementDTO.getAdvertisementId()));
        viewService.save(advertisementDTO.getAdvertisementId());

        return advertisementDTO;
    }

    public void promotionAd(int advertisementId) {
        persistenceAdvertisementService.updatePromotionDate(advertisementId);
    }

    public List<Advertisement> getFilteredAdsByKeyword(String keyword) {
        List<Advertisement> advertisementList = getAllActive();
        return advertisementList.stream()
                .filter(ad -> (ad.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        ad.getDescription().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }

    public List<Advertisement> getFilteredAdsByKeyword(List<Advertisement> advertisements, String keyword) {
        Objects.requireNonNull(advertisements);
        return advertisements.stream()
                .filter(ad -> (ad.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        ad.getDescription().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }

    public List<Advertisement> getPublishedFilteredAds(FilterDTO filterDTO) {
        if (Objects.isNull(filterDTO)) {
            return getAllActive();
        }
        return persistenceAdvertisementService.getPublishedFilteredAds(filterDTO);
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
                updateAdvertisement(advertisementMapper.convertToEditedAdvertisementDTO(ad));
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

        var parsedDate = LocalDateTime.parse(publicationDate);
        if (parsedDate.isBefore(LocalDateTime.now())) {
            throw new BadRequestException();
        } else {
            advertisement.setPublicationDate(parsedDate);
            return persistenceAdvertisementService.updateAdvertisement(advertisement);
        }
    }

    public int cancelDelayedPublication(int id) {
        Advertisement advertisement = findDraftAdById(id)
                .orElseThrow(NotFoundException::new);

        advertisement.setPublicationDate(LocalDateTime.now());
        return persistenceAdvertisementService.updateAdvertisement(advertisement);
    }

    public List<Advertisement> sort(List<Advertisement> ads, String sortOrder) {
        Objects.requireNonNull(sortOrder);
        switch (sortOrder) {
            case "price_asc":
                return sortByCheapest(ads);
            case "price_desc":
                return sortByExpensive(ads);
            case "title_asc":
                return sortByTitleAsc(ads);
            case "title_desc":
                return sortByTitleDesc(ads);
            case "city_asc":
                return sortByCityAsc(ads);
            case "city_desc":
                return sortByCityDesc(ads);
            case "oldest":
                return sortByOldest(ads);
            case "recent":
            default:
                return sortByRecent(ads);
        }
    }

    public List<Advertisement> sortByRecent(List<Advertisement> ads) {
        Objects.requireNonNull(ads);
        return ads.stream()
                .sorted(Comparator.comparing(Advertisement::getCreationDate).reversed())
                .collect(Collectors.toList());
    }

    public List<Advertisement> sortByOldest(List<Advertisement> ads) {
        Objects.requireNonNull(ads);
        return ads.stream()
                .sorted(Comparator.comparing(Advertisement::getCreationDate))
                .collect(Collectors.toList());
    }

    public List<Advertisement> sortByExpensive(List<Advertisement> ads) {
        Objects.requireNonNull(ads);
        return ads.stream()
                .sorted(Comparator.comparing(Advertisement::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public List<Advertisement> sortByCheapest(List<Advertisement> ads) {
        Objects.requireNonNull(ads);
        return ads.stream()
                .sorted(Comparator.comparing(Advertisement::getPrice))
                .collect(Collectors.toList());
    }

    public List<Advertisement> sortByTitleAsc(List<Advertisement> ads) {
        Objects.requireNonNull(ads);
        return ads.stream()
                .sorted(Comparator.comparing(Advertisement::getTitle, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public List<Advertisement> sortByTitleDesc(List<Advertisement> ads) {
        Objects.requireNonNull(ads);
        return ads.stream()
                .sorted(Comparator.comparing(Advertisement::getTitle, String.CASE_INSENSITIVE_ORDER).reversed())
                .collect(Collectors.toList());
    }

    public List<Advertisement> sortByCityAsc(List<Advertisement> ads) {
        Objects.requireNonNull(ads);
        return ads.stream()
                .sorted(Comparator.comparing(Advertisement::getCity, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public List<Advertisement> sortByCityDesc(List<Advertisement> ads) {
        Objects.requireNonNull(ads);
        return ads.stream()
                .sorted(Comparator.comparing(Advertisement::getCity, String.CASE_INSENSITIVE_ORDER).reversed())
                .collect(Collectors.toList());
    }
}
