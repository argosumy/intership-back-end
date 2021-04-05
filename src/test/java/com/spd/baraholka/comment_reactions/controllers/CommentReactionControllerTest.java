package com.spd.baraholka.comment_reactions.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spd.baraholka.comment_reactions.dto.CommentReactionDto;
import com.spd.baraholka.comment_reactions.enums.CommentReactionType;
import com.spd.baraholka.comment_reactions.mappers.CommentReactionDtoMapper;
import com.spd.baraholka.comment_reactions.services.CommentReactionService;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.comments.services.CommentService;
import com.spd.baraholka.config.SecurityConfig;
import com.spd.baraholka.login.controller.OAuth2AuthenticationSuccessHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.spd.baraholka.comment_reactions.enums.CommentReactionType.DISLIKE;
import static com.spd.baraholka.comment_reactions.enums.CommentReactionType.LIKE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class CommentReactionControllerTest {

    @Autowired
    @MockBean
    private CommentReactionService commentReactionService;
    @Autowired
    @MockBean
    private CommentService commentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    @MockBean
    private CommentReactionDtoMapper commentReactionDtoMapper;
    @Autowired
    private ObjectMapper mapper;
    private CommentReactionDto commentReactionDto;
    @MockBean
    private Comment comment;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    @MockBean
    @Qualifier("OAuth2SuccessHandler")
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentReactionDto = createCommentReactionDto();
    }

    private CommentReactionDto createCommentReactionDto() {
        return new CommentReactionDto(1, DISLIKE, 1, 1);
    }

    @Test
    void getTotalReactionTypeByCommentId() throws Exception {
        when(commentReactionService.getTotalReactionTypeByCommentId(1, LIKE))
                .thenReturn(1);

        mockMvc.perform(get("/comment-reactions/1/LIKE"))
                .andExpect(status().isOk());
    }

    @Test
    void saveNew() throws Exception {
        mockMvc.perform(
                post("/comment-reaction").contentType(MediaType.APPLICATION_JSON)
                        .content(getRequestJson()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCommentReaction() throws Exception {
        when(commentService.findById(1))
                .thenReturn(Optional.ofNullable(comment));

        mockMvc.perform(delete("/comment-reaction/1/LIKE"))
                .andExpect(status().isOk());

        verify(commentReactionService).deleteLastRecordByCommentId(1, CommentReactionType.LIKE);
    }

    private String getRequestJson() throws JsonProcessingException {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(commentReactionDto);
    }
}