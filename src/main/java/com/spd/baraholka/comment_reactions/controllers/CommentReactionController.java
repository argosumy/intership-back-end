package com.spd.baraholka.comment_reactions.controllers;

import com.spd.baraholka.comment_reactions.dto.CommentReactionDto;
import com.spd.baraholka.comment_reactions.entities.CommentReaction;
import com.spd.baraholka.comment_reactions.enums.CommentReactionType;
import com.spd.baraholka.comment_reactions.mappers.CommentReactionDtoMapper;
import com.spd.baraholka.comment_reactions.services.CommentReactionService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentReactionController {

    private final CommentReactionService commentReactionService;
    private final CommentReactionDtoMapper commentReactionDtoMapper;

    public CommentReactionController(CommentReactionService commentReactionService,
                                     CommentReactionDtoMapper commentReactionDtoMapper) {
        this.commentReactionService = commentReactionService;
        this.commentReactionDtoMapper = commentReactionDtoMapper;
    }

    @GetMapping("/comment-reactions/{commentId}/{commentReaction}")
    public int getTotalReactionTypeByCommentId(@PathVariable("commentId") int commentId,
                                               @PathVariable("commentReaction") CommentReactionType commentReactionType) {
        return commentReactionService.getTotalReactionTypeByCommentId(commentId, commentReactionType);
    }

    @PostMapping("/comment-reaction")
    public CommentReaction saveNew(@RequestBody CommentReactionDto commentReactionDto) {
        CommentReaction commentReaction = commentReactionDtoMapper.toCommentReaction(commentReactionDto);
        return commentReactionService.saveNew(commentReaction);
    }

    @DeleteMapping("/comment-reaction/{commentId}/{commentReaction}")
    public void deleteCommentReaction(@PathVariable("commentId") int commentId,
                                      @PathVariable("commentReaction") CommentReactionType commentReactionType) {
        commentReactionService.deleteLastRecordByCommentId(commentId, commentReactionType);
    }
}
