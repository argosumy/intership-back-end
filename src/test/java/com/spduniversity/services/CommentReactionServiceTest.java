package com.spduniversity.services;

import com.spduniversity.entities.comments.Comment;
import com.spduniversity.entities.comments.CommentReaction;
import com.spduniversity.entities.enums.CommentReactionType;
import com.spduniversity.entities.users.User;
import com.spduniversity.repositories.comment_reactions.CommentReactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CommentReactionServiceTest {

    @Mock
    private CommentReactionRepository commentReactionRepository;
    private CommentReactionService commentReactionService;
    private CommentReaction commentReaction;
    private Comment comment;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentReactionService = new CommentReactionService(commentReactionRepository);
        comment = createComment();
        user = createUser();
        commentReaction = createCommentReaction();
    }

    private CommentReaction createCommentReaction() {
        CommentReaction commentReaction = new CommentReaction();
        commentReaction.setCommentReactionType(CommentReactionType.DISLIKE);
        commentReaction.setComment(comment);
        commentReaction.setUser(user);
        return commentReaction;
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
    @DisplayName("Should return actual amount of comment reactions")
    void getTotalReactionTypeByCommentId() {
        when(commentReactionService.getTotalReactionTypeByCommentId(1, "LIKE"))
                .thenReturn(1);
        int size = commentReactionRepository.getTotalReactionTypeByCommentId(1, "LIKE");

        assertThat(size).isEqualTo(1);
    }

    @Test
    @DisplayName("Should correctly save and return comment reaction")
    void saveNew() {
        when(commentReactionService.saveNew(commentReaction)).thenReturn(commentReaction);
        CommentReaction savedCommentReaction = commentReactionService.saveNew(commentReaction);

        assertThat(savedCommentReaction).isEqualTo(commentReaction);
    }

    @Test
    @DisplayName("Should delete comment reaction by comment id")
    void deleteLastRecordByCommentId() {
        commentReactionService.deleteLastRecordByCommentId(1, "DISLIKE");

        verify(commentReactionRepository).deleteLastRecordByCommentId(1, "DISLIKE");
    }
}