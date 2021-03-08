package com.spduniversity.controllers;

import com.spduniversity.dto.CommentReactionDto;
import com.spduniversity.entities.comments.CommentReaction;
import com.spduniversity.exceptions.comment_reactions.CommentReactionNoContentException;
import com.spduniversity.services.CommentReactionService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentReactionController {

    private final CommentReactionService commentReactionService;

    public CommentReactionController(CommentReactionService commentReactionService) {
        this.commentReactionService = commentReactionService;
    }

    @GetMapping("/comment-reactions/{commentId}/{commentReaction}")
    int getTotalReactionTypeByCommentId(@PathVariable("commentId") int commentId,
                                        @PathVariable("commentReaction") String commentReaction) {
        return commentReactionService.getTotalReactionTypeByCommentId(commentId, commentReaction);
    }

    @PostMapping("/comment-reactions")
    CommentReaction saveNew(@RequestBody CommentReactionDto commentReactionDto) {
        if (commentReactionDto == null) {
            throw new CommentReactionNoContentException();
        }
        CommentReaction commentReaction = CommentReactionDto.toCommentReaction(commentReactionDto);
        return commentReactionService.saveNew(commentReaction);
    }

    @DeleteMapping("/comment-reactions/{commentId}/{commentReaction}")
    void deleteCommentReaction(@PathVariable("commentId") int commentId,
                               @PathVariable("commentReaction") String commentReaction) {
        commentReactionService.deleteLastRecordByCommentId(commentId, commentReaction);
    }
}
