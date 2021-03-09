package com.spd.baraholka.comments.services;

import com.spd.baraholka.advertisements.entities.Advertisement;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.users.entities.User;
import com.spd.baraholka.comments.repositories.CommentRepository;
import com.spd.baraholka.comments.services.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;
    private CommentService commentService;
    private Comment comment;
    private Advertisement advertisement;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentService = new CommentService(commentRepository);
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
    @DisplayName("Should return list of comments by advertisement id")
    void getAllByAdId() {
        when(commentService.getAllByAdId(1)).thenReturn(List.of(comment));
        List<Comment> commentList = commentService.getAllByAdId(1);

        assertThat(commentList).isEqualTo(List.of(comment));
    }

    @Test
    @DisplayName("Should return empty list of comments by advertisement id")
    void getEmptyCommentListByAdId() {
        when(commentService.getAllByAdId(2)).thenReturn(Collections.emptyList());
        List<Comment> commentList = commentService.getAllByAdId(2);

        assertThat(commentList).isEmpty();
    }

    @Test
    @DisplayName("Should return empty list of comments by advertisement id")
    void getListOfThreeCommentsByAdId() {
        when(commentService.getLimitCommentsByAdId(1)).thenReturn(List.of(comment, comment, comment));
        List<Comment> commentList = commentService.getAllByAdId(1);

        assertThat(commentList.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Should correctly save and return comment")
    void saveNew() {
        when(commentService.saveNew(comment)).thenReturn(comment);
        Comment savedComment = commentService.saveNew(comment);

        assertThat(savedComment).isEqualTo(comment);
    }

    @Test
    @DisplayName("Should delete comment by id")
    void deleteById() {
        commentService.deleteById(1);

        verify(commentRepository).deleteById(1);
    }

    @Test
    @DisplayName("Should find comment by id")
    void findById() {
        when(commentService.findById(1)).thenReturn(Optional.ofNullable(comment));
        Optional<Comment> returnedComment = commentService.findById(1);

        assertThat(returnedComment).contains(comment);
    }

    @Test
    @DisplayName("Should return Optional.empty() in case of unFinding comment by id")
    void unFindById() {
        when(commentService.findById(2)).thenReturn(Optional.empty());
        Optional<Comment> returnedComment = commentService.findById(2);

        assertThat(returnedComment).isEmpty();
    }

    @Test
    @DisplayName("Should return correctly updated comment")
    void update() {
        Comment updatedComment = new Comment(
                1,
                "UPDATED COMMENT",
                LocalDate.of(2021, 1, 1),
                advertisement,
                user,
                null
        );
        when(commentService.update(updatedComment, 1)).thenReturn(updatedComment);
        Comment returnedComment = commentService.update(updatedComment, 1);

        assertThat(returnedComment).isEqualTo(updatedComment);
    }
}