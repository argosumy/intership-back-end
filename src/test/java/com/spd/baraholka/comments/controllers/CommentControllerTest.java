package com.spd.baraholka.comments.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.comments.dto.CommentDto;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.comments.exceptions.CommentNotFoundException;
import com.spd.baraholka.comments.mappers.CommentDtoMapper;
import com.spd.baraholka.comments.services.CommentService;
import com.spd.baraholka.config.SecurityConfig;
import com.spd.baraholka.login.controller.OAuth2AuthenticationSuccessHandler;
import com.spd.baraholka.user.persistance.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    @MockBean
    private CommentService commentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    @MockBean
    private CommentDtoMapper commentDtoMapper;
    @Autowired
    private ObjectMapper mapper;
    private CommentDto commentDto;
    private Comment comment;
    private Advertisement advertisement;
    private User user;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    @MockBean
    @Qualifier("OAuth2SuccessHandler")
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @BeforeEach
    void setUp() {
        advertisement = createAdvertisement();
        user = createUser();
        comment = createComment();
        commentDto = createCommentDto();
    }

    private Comment createComment() {
        return new Comment(
                1,
                "test comment text",
                LocalDate.of(2021, 3, 7),
                advertisement,
                user,
                null
        );
    }

    private CommentDto createCommentDto() {
        return new CommentDto(
                1,
                "test comment text",
                LocalDate.of(2021, 3, 7),
                1,
                1,
                1
        );
    }

    private User createUser() {
        User user = new User();
        user.setId(1);
        return user;
    }

    private Advertisement createAdvertisement() {
        Advertisement advertisement = new Advertisement();
        advertisement.setId(1);
        return advertisement;
    }

    @Test
    @DisplayName("Show list of all comments by ad id")
    void getCommentsByAdId() throws Exception {
        when(commentService.getAllByAdId(1)).thenReturn(
                List.of(comment)
        );

        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Show list of three comments")
    void getLimitCommentsByAdId() throws Exception {
        when(commentService.getAllByAdId(1)).thenReturn(
                List.of(comment, comment, comment)
        );

        mockMvc.perform(get("/comments/limit/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Comment was saved")
    void saveComment() throws Exception {
        mockMvc.perform(
                post("/comments").contentType(MediaType.APPLICATION_JSON)
                        .content(getRequestJson()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Comment was deleted")
    void deleteComment() throws Exception {
        mockMvc.perform(delete("/comment/1"))
                .andExpect(status().isOk());

        verify(commentService).deleteById(1);
    }

    @Test
    @DisplayName("Comment was updated")
    void updateComment() throws Exception {
        when(commentService.findById(1))
                .thenReturn(Optional.ofNullable(comment));

        mockMvc.perform(put("/comment/1").contentType(MediaType.APPLICATION_JSON)
                .content(getRequestJson()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Comment was found by id")
    void getOneComment() throws Exception {
        when(commentService.findById(1))
                .thenReturn(Optional.ofNullable(comment));

        mockMvc.perform(
                get("/comment/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Comment not found by id")
    void commentNotFoundById() throws Exception {
        when(commentService.findById(2))
                .thenThrow(new CommentNotFoundException(100));

        mockMvc.perform(get("/comment/100"))
                .andExpect(status().isNotFound());
    }

    private String getRequestJson() throws JsonProcessingException {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(commentDto);
    }
}