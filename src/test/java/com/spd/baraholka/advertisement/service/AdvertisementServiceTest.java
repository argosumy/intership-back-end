package com.spd.baraholka.advertisement.service;

import com.spd.baraholka.advertisement.controller.mappers.AdvertisementMapper;
import com.spd.baraholka.advertisement.persistance.PersistenceAdvertisementService;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.persistance.entities.CurrencyType;
import com.spd.baraholka.advertisement.persistance.repositories.AdvertisementRepository;
import com.spd.baraholka.characteristic.service.CharacteristicServiceImpl;
import com.spd.baraholka.config.exceptions.NotFoundException;
import com.spd.baraholka.notification.service.Sender;
import com.spd.baraholka.user.service.OwnerService;
import com.spd.baraholka.views.service.ViewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class AdvertisementServiceTest {

    @Mock
    private AdvertisementRepository advertisementRepository;
    @Mock
    private PersistenceAdvertisementService persistenceAdvertisementService;
    @Mock
    private OwnerService ownerService;
    @Mock
    private CharacteristicServiceImpl characteristicService;
    @Mock
    private Sender sender;
    @Mock
    private ViewService viewService;
    private AdvertisementService advertisementService;
    private Advertisement advertisementActive;
    private Advertisement advertisementDraft;
    private Advertisement advertisementDraftToBeShown;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        AdvertisementMapper advertisementMapper = new AdvertisementMapper();
        advertisementService = new AdvertisementService(persistenceAdvertisementService, characteristicService, advertisementMapper, ownerService, sender, viewService);
        advertisementDraft = createAdvertisement(1, LocalDateTime.of(2022, 1, 1, 10, 40, 1),
                AdvertisementStatus.DRAFT);
        advertisementActive = createAdvertisement(2, LocalDateTime.of(2019, 2, 2, 2, 10, 2),
                AdvertisementStatus.ACTIVE);
        advertisementDraftToBeShown = createAdvertisement(3, LocalDateTime.of(2019, 2, 2, 2, 10, 2),
                AdvertisementStatus.DRAFT);
    }

    private Advertisement createAdvertisement(int id, LocalDateTime publication, AdvertisementStatus status) {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementId(id);
        advertisement.setOwnerId(1);
        advertisement.setTitle("title");
        advertisement.setDescription("description");
        advertisement.setCategory("category");
        advertisement.setPrice(100);
        advertisement.setCurrency(CurrencyType.UAH);
        advertisement.setDiscountAvailability(true);
        advertisement.setCity("city");
        advertisement.setStatus(status);
        advertisement.setCreationDate(LocalDateTime.of(2021, 3, 14, 9, 48, 56));
        advertisement.setPublicationDate(publication);
        advertisement.setStatusChangeDate(LocalDateTime.of(2021, 3, 14, 9, 48, 56));
        return advertisement;
    }

    @Test
    @DisplayName("Must show 'DRAFT' ad and change its status to 'ACTIVE'")
    void getDraftToBeShownAd() {
        when(advertisementService.getAllActive()).thenReturn(List.of(advertisementDraftToBeShown));
        List<Advertisement> advertisementList = advertisementService.getAllActive();

        assertThat(advertisementList.get(0).getStatus()).isEqualTo(AdvertisementStatus.ACTIVE);
    }

    @Test
    @DisplayName("Should find ad by id")
    void findDraftAdById() {
        when(advertisementService.findDraftAdById(1)).thenReturn(Optional.ofNullable(advertisementDraft));
        Optional<Advertisement> returnedAdvertisement = advertisementService.findDraftAdById(1);

        assertThat(returnedAdvertisement).contains(advertisementDraft);
    }

    @Test
    @DisplayName("Should return Optional.empty() in case of unFinding advertisement by id")
    void unFindById() {
        when(advertisementService.findDraftAdById(10)).thenReturn(Optional.empty());
        Optional<Advertisement> returnedAdvertisement = advertisementService.findDraftAdById(10);

        assertThat(returnedAdvertisement).isEmpty();
    }

    @Test
    @DisplayName("Should set publication date")
    void editPublicationDate() {
        when(advertisementService.findDraftAdById(1))
                .thenReturn(Optional.ofNullable(advertisementDraft));
        advertisementService.editPublicationDate(1, "3333-01-01T10:40:01");

        assertThat(advertisementDraft.getPublicationDate()).isEqualTo(LocalDateTime.of(3333, 1, 1, 10, 40, 1));
    }

    @Test
    @DisplayName("Couldn't find ad by id for editing publication date and thew exception")
    void editPublicationDateThrowException() {
        when(advertisementService.findDraftAdById(100))
                .thenThrow(new NotFoundException());

        assertThrows(NotFoundException.class,
                () -> advertisementService.editPublicationDate(100, "3333-01-01T10:40:01"),
                "Not found!"
        );
    }
}