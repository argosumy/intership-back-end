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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static com.spd.baraholka.comment_reactions.enums.CommentReactionType.LIKE;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdvertisementController.class)
class AdvertisementControllerTest {

    @Autowired
    @MockBean
    private AdvertisementService advertisementService;
    @Autowired
    @MockBean
    private AdvertisementMapper advertisementMapper;
    @Autowired
    private MockMvc mockMvc;
    private Advertisement advertisementActive;
    private Advertisement advertisementDraft;
    private Advertisement advertisementDraftToBeShown;
    @Autowired
    private ObjectMapper mapper;

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
    void getFilteredAdsByTitle() throws Exception {
        when(advertisementService.getFilteredAdsByTitle("title")).thenReturn(
                List.of(advertisementActive, advertisementDraftToBeShown)
        );

        mockMvc.perform(get("/advertisements/title-search?title=title")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getResponseJson(List.of(advertisementActive, advertisementDraftToBeShown))))
                .andExpect(status().isOk());
    }

    @Test
    void getFilteredAdsByDescription() throws Exception {
        when(advertisementService.getFilteredAdsByDescription("description")).thenReturn(
                List.of(advertisementActive, advertisementDraftToBeShown)
        );

        mockMvc.perform(get("/advertisements/description-search?description=description")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getResponseJson(List.of(advertisementActive, advertisementDraftToBeShown))))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return list of active comments")
    void getAllActiveAds() throws Exception {
        when(advertisementService.getAllActive()).thenReturn(
                List.of(advertisementActive, advertisementDraftToBeShown)
        );

        mockMvc.perform(get("/advertisements")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getResponseJson(List.of(advertisementActive, advertisementDraftToBeShown))))
                .andExpect(status().isOk());
    }

    @Test
    void editPublicationDate() throws Exception {
        when(advertisementService.editPublicationDate(advertisementDraft, "2023-01-01T10:40:01"))
                .thenReturn(1);

//        mockMvc.perform(get("/comment-reactions/1/LIKE"))
//                .andExpect(status().isOk());

        mockMvc.perform(put("/advertisements/1/publish-delayed?publicationDate=2023-01-01T10:40:01")
                .contentType(MediaType.APPLICATION_JSON)
                .content("1"))
                .andExpect(status().isOk());
//                .contentType(MediaType.TEXT_HTML)
//                .content("1"));
//                .andExpect(status().isOk());

//        verify(advertisementService).editPublicationDate(advertisementDraft, "2023-01-01T10:40:01");
    }

    @Test
    void cancelDelayedPublicationOfExistsAd() {
    }

    private String getResponseJson(List<Advertisement> advertisements) throws JsonProcessingException {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(advertisements);
    }
}