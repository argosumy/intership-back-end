package com.spd.baraholka.comments.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spd.baraholka.advertisements.entities.Advertisement;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.comments.exceptions.CommentNotFoundException;
import com.spd.baraholka.comments.mappers.CommentDtoMapper;
import com.spd.baraholka.comments.services.CommentService;
import com.spd.baraholka.users.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

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
    private Comment comment;
    private Advertisement advertisement;
    private User user;

    @BeforeEach
    void setUp() {
        advertisement = createAdvertisement();
        user = createUser();
        comment = createComment();
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
    void getCommentsByAdId() throws Exception {
        when(commentService.getAllByAdId(1)).thenReturn(
                List.of(comment)
        );

        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getLimitCommentsByAdId() throws Exception {
        when(commentService.getAllByAdId(1)).thenReturn(
                List.of(comment, comment, comment)
        );

        mockMvc.perform(get("/comments/limit/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void saveComment() throws Exception {
        mockMvc.perform(
                post("/comments").contentType(MediaType.APPLICATION_JSON)
                        .content(getRequestJson()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteComment() throws Exception {
        mockMvc.perform(delete("/comment/1"))
                .andExpect(status().isOk());

        verify(commentService).deleteById(1);
    }

    @Test
    void updateComment() throws Exception {
        when(commentService.findById(1))
                .thenReturn(Optional.ofNullable(comment));

        mockMvc.perform(put("/comment/1").contentType(MediaType.APPLICATION_JSON)
                .content(getRequestJson()))
                .andExpect(status().isOk());
    }

    @Test
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
                .thenThrow(new CommentNotFoundException(2));

        mockMvc.perform(get("/comment/2"))
                .andExpect(status().isNotFound());
    }

    private String getRequestJson() throws JsonProcessingException {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(comment);
    }
}