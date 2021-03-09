package com.spd.baraholka.comment_reactions.controllers;

import com.spd.baraholka.comment_reactions.dto.CommentReactionDto;
import com.spd.baraholka.comment_reactions.entities.CommentReaction;
import com.spd.baraholka.comment_reactions.exceptions.CommentReactionBadRequestException;
import com.spd.baraholka.comment_reactions.exceptions.CommentReactionNoContentException;
import com.spd.baraholka.comment_reactions.services.CommentReactionService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentReactionController {

    private final CommentReactionService commentReactionService;

    public CommentReactionController(CommentReactionService commentReactionService) {
        this.commentReactionService = commentReactionService;
    }

    @GetMapping("/comment-reactions/{commentId}/{commentReaction}")
    public int getTotalReactionTypeByCommentId(@PathVariable("commentId") int commentId,
                                               @PathVariable("commentReaction") String commentReaction) {
        if (commentReaction == null) {
            throw new CommentReactionBadRequestException();
        }
        return commentReactionService.getTotalReactionTypeByCommentId(commentId, commentReaction);
    }

    @PostMapping("/comment-reaction")
    public CommentReaction saveNew(@RequestBody CommentReactionDto commentReactionDto) {
        if (commentReactionDto == null) {
            throw new CommentReactionNoContentException();
        }
        CommentReaction commentReaction = CommentReactionDto.toCommentReaction(commentReactionDto);
        return commentReactionService.saveNew(commentReaction);
    }

    @DeleteMapping("/comment-reaction/{commentId}/{commentReaction}")
    public void deleteCommentReaction(@PathVariable("commentId") int commentId,
                                      @PathVariable("commentReaction") String commentReaction) {
        if (commentReaction == null) {
            throw new CommentReactionBadRequestException();
        }
        commentReactionService.deleteLastRecordByCommentId(commentId, commentReaction);
    }
}
