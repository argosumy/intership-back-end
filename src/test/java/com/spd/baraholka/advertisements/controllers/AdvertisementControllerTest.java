package com.spd.baraholka.advertisements.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.advertisements.persistance.AdvertisementStatus;
import com.spd.baraholka.advertisements.persistance.CurrencyType;
import com.spd.baraholka.advertisements.services.AdvertisementMapper;
import com.spd.baraholka.advertisements.services.AdvertisementService;
import com.spd.baraholka.advertisements.services.AdvertisementUserEmailMapper;
import com.spd.baraholka.config.SecurityConfig;
import com.spd.baraholka.config.exceptions.NotFoundException;
import com.spd.baraholka.login.controller.OAuth2AuthenticationSuccessHandler;
import com.spd.baraholka.pagination.entities.PageRequest;
import com.spd.baraholka.pagination.services.PageRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AdvertisementController.class)
class AdvertisementControllerTest {

    @Autowired
    @MockBean
    private AdvertisementService advertisementService;
    @Autowired
    @MockBean
    private PageRequestService pageRequestService;
    @Autowired
    @MockBean
    private AdvertisementMapper advertisementMapper;
    @Autowired
    @MockBean
    private AdvertisementUserEmailMapper advertisementUserEmailMapper;
    @Autowired
    private MockMvc mockMvc;
    private Advertisement advertisementActive;
    private Advertisement advertisementDraft;
    private Advertisement advertisementDraftToBeShown;
    private PageRequest<Advertisement> pageRequest;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    @MockBean
    @Qualifier("OAuth2SuccessHandler")
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        AdvertisementMapper advertisementMapper = new AdvertisementMapper();
        advertisementDraft = createAdvertisement(1, LocalDateTime.of(2022, 1, 1, 10, 40, 1),
                AdvertisementStatus.DRAFT);
        advertisementActive = createAdvertisement(2, LocalDateTime.of(2019, 2, 2, 2, 10, 2),
                AdvertisementStatus.ACTIVE);
        advertisementDraftToBeShown = createAdvertisement(3, LocalDateTime.of(2019, 2, 2, 2, 10, 2),
                AdvertisementStatus.DRAFT);
        pageRequest = createPageRequest();
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

    private PageRequest<Advertisement> createPageRequest() {
        PageRequest<Advertisement> pageRequest = new PageRequest<>();
        pageRequest.setPageNumber(1);
        pageRequest.setPageSize(2);
        pageRequest.setTotalPages(1);
        pageRequest.setContent(List.of(advertisementActive, advertisementDraftToBeShown));
        return pageRequest;
    }

    @Test
    @DisplayName("Expect list of advertisements filtered by keyword")
    void getFilteredAdsByKeyword() throws Exception {
        when(advertisementService.getFilteredAdsByKeyword("title", null)).thenReturn(
                List.of(advertisementActive, advertisementDraftToBeShown)
        );

        mockMvc.perform(get("/advertisements/search?keyword=title&size=")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getResponseJson(List.of(advertisementActive, advertisementDraftToBeShown))))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Advertisement was found by id and updated publication date")
    void editPublicationDate() throws Exception {
        when(advertisementService.findDraftAdById(1))
                .thenReturn(Optional.ofNullable(advertisementDraft));

        mockMvc.perform(put("/advertisements/1/publish-delayed?publicationDate=2023-01-01T10:40:01")
                .contentType(MediaType.TEXT_HTML)
                .content("1"))
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    @DisplayName("Advertisement was not found by id and threw exception")
    void editPublicationDateOfNotExistsAdAndThrowException() throws Exception {
        when(advertisementService.editPublicationDate(100, "2023-01-01T10:40:01"))
                .thenThrow(new NotFoundException());

        mockMvc.perform(put("/advertisements/100/publish-delayed?publicationDate=2023-01-01T10:40:01"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andExpect(result -> assertEquals("Not found!",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @DisplayName("Advertisement was found by id and canceled delayed publication")
    void cancelDelayedPublicationOfExistsAd() throws Exception {
        when(advertisementService.findDraftAdById(1))
                .thenReturn(Optional.ofNullable(advertisementDraft));

        mockMvc.perform(put("/advertisements/1/cancel-delayed")
                .contentType(MediaType.TEXT_HTML)
                .content("1"))
                .andExpect(status().isOk());
    }

    private String getResponseJson(Object object) throws JsonProcessingException {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }
}