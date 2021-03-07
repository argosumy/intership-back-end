package com.spduniversity.services;

import com.spduniversity.entities.advertisements.Advertisement;
import com.spduniversity.entities.comments.Comment;
import com.spduniversity.entities.users.User;
import com.spduniversity.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
    void getAllByAdId() {
        when(commentService.getAllByAdId(1)).thenReturn(List.of(comment));
        List<Comment> commentList = commentService.getAllByAdId(1);

        assertThat(commentList).isEqualTo(List.of(comment));
    }

    @Test
    void saveNew() {
        when(commentService.saveNew(comment)).thenReturn(comment);
        Comment savedComment = commentService.saveNew(comment);

        assertThat(savedComment).isEqualTo(comment);
    }

    @Test
    void deleteById() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }
}