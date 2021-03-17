package com.spd.baraholka.advertisements.services;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.advertisements.persistance.AdvertisementRepository;
import com.spd.baraholka.advertisements.persistance.AdvertisementStatus;
import com.spd.baraholka.advertisements.persistance.CurrencyType;
import com.spd.baraholka.config.exceptions.NotFoundByIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
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
    private AdvertisementService advertisementService;
    private Advertisement advertisementActive;
    private Advertisement advertisementDraft;
    private Advertisement advertisementDraftToBeShown;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        AdvertisementMapper advertisementMapper = new AdvertisementMapper();
        advertisementService = new AdvertisementService(persistenceAdvertisementService, advertisementMapper);
        advertisementDraft = createAdvertisement(1, LocalDateTime.of(2022, 1, 1, 10, 40, 1),
                AdvertisementStatus.DRAFT);
        advertisementActive = createAdvertisement(2, LocalDateTime.of(2019, 2, 2, 2, 10, 2),
                AdvertisementStatus.ACTIVE);
        advertisementDraftToBeShown = createAdvertisement(3, LocalDateTime.of(2019, 2, 2, 2, 10, 2),
                AdvertisementStatus.DRAFT);

    }

    private Advertisement createAdvertisement(int id, LocalDateTime publication, AdvertisementStatus status) {
        return new Advertisement(
                id,
                1,
                "title",
                "description",
                "category",
                100,
                CurrencyType.UAH,
                true,
                "city",
                status,
                LocalDateTime.of(2021, 3, 14, 9, 48, 56),
                publication,
                LocalDateTime.of(2021, 3, 14, 9, 48, 56)
        );
    }

    @Test
    @DisplayName("Should return full list of filtered ads by keyword in case returned size not defined")
    void getFilteredAdsByKeyword() {
        when(advertisementService.getFilteredAdsByKeyword("title", null)).thenReturn(List.of(advertisementActive, advertisementDraftToBeShown));
        List<Advertisement> advertisementList = advertisementService.getFilteredAdsByKeyword("title", null);

        assertThat(advertisementList).isEqualTo(List.of(advertisementActive, advertisementDraftToBeShown));
    }

    @Test
    @DisplayName("Should return full list of filtered ads by description matching keyword in upper case when returned size not defined")
    void getFilteredAdsFoundByDescriptionKeyword() {
        when(advertisementService.getFilteredAdsByKeyword("DESCRIPTION", null)).thenReturn(List.of(advertisementActive, advertisementDraftToBeShown));
        List<Advertisement> advertisementList = advertisementService.getFilteredAdsByKeyword("DESCRIPTION", null);

        assertThat(advertisementList).isEqualTo(List.of(advertisementActive, advertisementDraftToBeShown));
    }

//    @Test
//    @DisplayName("Should return full list of filtered ads by description in upper case when returned size not defined")
//    void getFilteredAdsFoundByDescriptionKeywordInUpperCase() {
//        when(advertisementService.getFilteredAdsByKeyword("DESCRIPTION", null)).thenReturn(List.of(advertisementActive, advertisementDraftToBeShown));
//        List<Advertisement> advertisementList = advertisementService.getFilteredAdsByKeyword("DESCRIPTION", null);
//
//        assertThat(advertisementList).isEqualTo(List.of(advertisementActive, advertisementDraftToBeShown));
//    }

    @Test
    @DisplayName("Should return only one entry of filtered ads by keyword in case size is defined as 1")
    void getFilteredAdsByKeywordWithDefinedSize() {
        when(advertisementService.getFilteredAdsByKeyword("i", 1)).thenReturn(List.of(advertisementActive));
        List<Advertisement> advertisementList = advertisementService.getFilteredAdsByKeyword("i", 1);

        assertThat(advertisementList).isEqualTo(List.of(advertisementActive));
    }

//    @Test
//    @DisplayName("Should return full list of filtered ads by keyword query in upper case")
//    void getFilteredAdsByKeywordInUpperCase() {
//        when(advertisementService.getFilteredAdsByKeyword("I", null)).thenReturn(List.of(advertisementActive, advertisementDraftToBeShown));
//        List<Advertisement> advertisementList = advertisementService.getFilteredAdsByKeyword("TITLE", null);
//
//        assertThat(advertisementList).isEqualTo(List.of(advertisementActive, advertisementDraftToBeShown));
//    }

    @Test
    @DisplayName("Should return empty list in case no ads found by keyword")
    void getEmptyListOfFilteredAdsByKeyword() {
        when(advertisementService.getFilteredAdsByKeyword("string", null)).thenReturn(Collections.emptyList());
        List<Advertisement> advertisementList = advertisementService.getFilteredAdsByKeyword("string", null);

        assertThat(advertisementList).isEmpty();
    }

    @Test
    @DisplayName("Should return list of ACTIVE ads")
    void getAllActive() {
        when(advertisementService.getAllActive()).thenReturn(List.of(advertisementActive));
        List<Advertisement> advertisementList = advertisementService.getAllActive();

        assertThat(advertisementList).isEqualTo(List.of(advertisementActive));
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
                .thenThrow(new NotFoundByIdException(100));

        assertThrows(NotFoundByIdException.class,
                () -> advertisementService.editPublicationDate(100, "3333-01-01T10:40:01"),
                "Could not find by id: 100"
        );
    }
}