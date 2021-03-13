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
import com.spd.baraholka.users.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.spd.baraholka.comment_reactions.enums.CommentReactionType.DISLIKE;
import static com.spd.baraholka.comment_reactions.enums.CommentReactionType.LIKE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentReactionController.class)
class CommentReactionControllerTest {

    @Autowired
    @MockBean
    private CommentReactionService commentReactionService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    @MockBean
    private CommentReactionDtoMapper commentReactionDtoMapper;
    @Autowired
    private ObjectMapper mapper;
    private CommentReactionDto commentReactionDto;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentReactionDto = createCommentReactionDto();
    }

    private CommentReactionDto createCommentReactionDto() {
        return new CommentReactionDto(1, DISLIKE, 1, 1);
    }

    private Comment createComment() {
        Comment comment = new Comment();
        comment.setId(1);
        return comment;
    }

    private User createUser() {
        User user = new User();
        user.setId(1);
        return user;
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