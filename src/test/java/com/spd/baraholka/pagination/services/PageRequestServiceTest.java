package com.spd.baraholka.pagination.services;

import com.spd.baraholka.advertisement.controller.mappers.AdvertisementMapper;
import com.spd.baraholka.advertisement.controller.mappers.AdvertisementUserEmailMapper;
import com.spd.baraholka.advertisement.persistance.PersistenceAdvertisementService;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.persistance.entities.CurrencyType;
import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.config.exceptions.NotFoundException;
import com.spd.baraholka.pagination.entities.PageRequest;
import com.spd.baraholka.user.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class PageRequestServiceTest {

    private AdvertisementService advertisementService;
    private PageRequestService pageRequestService;
    @Mock
    private AdvertisementMapper advertisementMapper;
    @Mock
    private AdvertisementUserEmailMapper advertisementUserEmailMapper;
    @Mock
    private PersistenceAdvertisementService persistenceAdvertisementService;
    @Mock
    private OwnerService ownerService;
    private PageRequest<Advertisement> pageRequest;
    private Advertisement advertisement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        advertisementService = new AdvertisementService(persistenceAdvertisementService, advertisementMapper, ownerService);
        pageRequestService = new PageRequestService(advertisementService);
        MockitoAnnotations.openMocks(this);
        advertisement = createAdvertisement();
        pageRequest = createPageRequest();
    }

    private Advertisement createAdvertisement() {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementId(2);
        advertisement.setOwnerId(1);
        advertisement.setTitle("title");
        advertisement.setDescription("description");
        advertisement.setCategory("category");
        advertisement.setPrice(100);
        advertisement.setCurrency(CurrencyType.UAH);
        advertisement.setDiscountAvailability(true);
        advertisement.setCity("city");
        advertisement.setStatus(AdvertisementStatus.ACTIVE);
        advertisement.setCreationDate(LocalDateTime.of(2021, 3, 14, 9, 48, 56));
        advertisement.setPublicationDate(LocalDateTime.of(2019, 2, 2, 2, 10, 2));
        advertisement.setStatusChangeDate(LocalDateTime.of(2021, 3, 14, 9, 48, 56));
        return advertisement;
    }

    private PageRequest<Advertisement> createPageRequest() {
        PageRequest<Advertisement> pageRequest = new PageRequest<>();
        pageRequest.setPageNumber(1);
        pageRequest.setPageSize(2);
        pageRequest.setTotalPages(3);
        pageRequest.setContent(List.of(advertisement, advertisement, advertisement, advertisement, advertisement));
        return pageRequest;
    }

    @Test
    @DisplayName("Should return correctly filled Page Request")
    void getPageRequest() {
        when(advertisementService.getAllActive()).thenReturn(List.of(advertisement));
        List<Advertisement> advertisementList = advertisementService.getAllActive();
        PageRequest<Advertisement> pageRequest = PageRequest.of(
                1, 2, 3, advertisementList);
        var allActive = advertisementService.getAllActive();
        assertThat(advertisementList).isEqualTo(pageRequestService.getPageRequest(2, 1, allActive).getContent());
    }

    @Test
    @DisplayName("Should throw NoContentException in case pageNumber > totalPages")
    void pageRequestThrowExceptionWhenPageNumberGreaterTotalPages() {
        when(advertisementService.getAllActive()).thenReturn(List.of(advertisement));
        List<Advertisement> advertisementList = advertisementService.getAllActive();
        PageRequest<Advertisement> pageRequest = PageRequest.of(
                4, 2, 3, advertisementList);

        assertThrows(NotFoundException.class,
                () -> pageRequestService.getPageRequest(2, 4, advertisementList),
                "Not found!"
        );
    }

    @Test
    @DisplayName("Should throw NoContentException in case pageNumber is equal to zero")
    void pageRequestThrowExceptionWhenPageNumberEqualZero() {
        when(advertisementService.getAllActive()).thenReturn(List.of(advertisement));
        List<Advertisement> advertisementList = advertisementService.getAllActive();
        PageRequest<Advertisement> pageRequest = PageRequest.of(
                0, 2, 3, advertisementList);

        assertThrows(NotFoundException.class,
                () -> pageRequestService.getPageRequest(2, 0, advertisementList),
                "Not found!"
        );
    }
}