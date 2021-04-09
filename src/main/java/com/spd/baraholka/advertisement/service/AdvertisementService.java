package com.spd.baraholka.advertisement.service;

import com.spd.baraholka.advertisement.controller.dto.EditedAdvertisementDTO;
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
import com.spd.baraholka.notification.service.Sender;
import com.spd.baraholka.user.controller.dto.OwnerDTO;
import com.spd.baraholka.user.service.OwnerService;
import com.spd.baraholka.views.service.ViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class AdvertisementService {

    private final PersistenceAdvertisementService persistenceAdvertisementService;
    private final AdvertisementMapper advertisementMapper;
    private static final Logger logger = LoggerFactory.getLogger(AdvertisementService.class);
    private final OwnerService ownerService;
    private final Sender sender;
    private final CharacteristicService characteristicService;
    private final ViewService viewService;

    public AdvertisementService(PersistenceAdvertisementService persistenceAdvertisementService,
                                CharacteristicService characteristicService,
                                AdvertisementMapper advertisementMapper,
                                OwnerService ownerService, @Lazy Sender sender,
                                ViewService viewService) {
        this.persistenceAdvertisementService = persistenceAdvertisementService;
        this.advertisementMapper = advertisementMapper;
        this.ownerService = ownerService;
        this.sender = sender;
        this.characteristicService = characteristicService;
        this.viewService = viewService;
    }

    public int saveAdvertisement(InitialAdvertisementDTO advertisementDTO, int userId) {
        Advertisement advertisement = advertisementMapper.convertToEntity(advertisementDTO);
        advertisement.setOwnerId(userId);
        int id = persistenceAdvertisementService.insertAdvertisement(advertisement);
        advertisement.setAdvertisementId(id);
//        sender.sendAllUsersNotification(advertisementMapper.convertToDTO(advertisement));    //TODO fix secure access
        advertisementDTO.getCharacteristics().forEach(characteristicDTO -> characteristicService.save(id, characteristicDTO));

        return id;
    }

    private int updateAdvertisement(EditedAdvertisementDTO advertisementDTO) {
        Advertisement advertisement = advertisementMapper.convertToEntity(advertisementDTO);
        characteristicService.update(advertisementDTO.getAdvertisementId(), advertisementDTO.getCharacteristics());

        return persistenceAdvertisementService.updateAdvertisement(advertisement);
    }

    public int updateAdvertisement(EditedAdvertisementDTO advertisementDTO, int userId) {
        Advertisement advertisement = advertisementMapper.convertToEntity(advertisementDTO);
        advertisement.setOwnerId(userId);
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
        addOwnerToAdvertisementDTO(advertisementDTO, advertisement.getOwnerId());
        addCharacteristicsToAdvertisementDTO(advertisementDTO);
        addViewsToAdvertisementDTO(advertisement, advertisementDTO);
        return advertisementDTO;
    }

    private void addViewsToAdvertisementDTO(Advertisement advertisement, FullAdvertisementDTO advertisementDTO) {
        int countOfViews = viewService.getCountOfViewsForAdvertisement(advertisement.getAdvertisementId());
        advertisementDTO.setViews(countOfViews);
    }

    private void addCharacteristicsToAdvertisementDTO(FullAdvertisementDTO advertisementDTO) {
        advertisementDTO.setCharacteristics(characteristicService.readForAdId(advertisementDTO.getAdvertisementId()));
        viewService.save(advertisementDTO.getAdvertisementId());
    }

    private void addOwnerToAdvertisementDTO(FullAdvertisementDTO advertisementDTO, int ownerId) {
        OwnerDTO owner = ownerService.getOwner(ownerId);
        advertisementDTO.setAdvertisementOwner(owner);
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

    public List<Advertisement> getAllActive() {
        List<Advertisement> active = persistenceAdvertisementService.getAllActive();
        logger.info("IN getAllActive - advertisements found: {}", active.size());

        setActiveStatus(active);
        addCountOfViews(active);
        return active;
    }

    private void addCountOfViews(List<Advertisement> advertisements) {
        advertisements.forEach(advertisement -> {
            int advertisementId = advertisement.getAdvertisementId();
            int countOfViews = viewService.getCountOfViewsForAdvertisement(advertisementId);
            advertisement.setViews(countOfViews);
        });
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
}
