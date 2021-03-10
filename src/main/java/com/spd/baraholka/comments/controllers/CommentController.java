package com.spd.baraholka.comments.controllers;

import com.spd.baraholka.comments.dto.CommentDto;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.comments.exceptions.CommentNoContentException;
import com.spd.baraholka.comments.exceptions.CommentNotFoundException;
import com.spd.baraholka.comments.exceptions.CommentsNotFoundException;
import com.spd.baraholka.comments.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments/{adId}")
    public List<CommentDto> getCommentsByAdId(@PathVariable("adId") int adId) {
        List<Comment> commentList = commentService.getAllByAdId(adId);
        return CommentDto.toCommentDtoList(commentList);
    }

    @GetMapping("/comments/limit/{adId}")
    public List<CommentDto> getLimitCommentsByAdId(@PathVariable("adId") int adId) {
        List<Comment> commentList = commentService.getLimitCommentsByAdId(adId);
        return CommentDto.toCommentDtoList(commentList);
    }

    @PostMapping("/comments")
    public Comment saveComment(@RequestBody CommentDto commentDto) {
        Comment comment = CommentDto.toComment(commentDto);
        return commentService.saveNew(comment);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable int id) {
        commentService.deleteById(id);
    }

    @PutMapping("/comment/{id}")
    public Comment updateComment(@RequestBody CommentDto commentDto, @PathVariable int id) {
        return commentService.findById(id)
                .map(com -> {
                    com.setId(commentDto.getId());
                    com.setBody(commentDto.getBody());
                    com.setCreatedDate(commentDto.getCreatedDate());
                    com.setAdvertisement(commentDto.getAdvertisement());
                    com.setUser(commentDto.getUser());
                    com.setParent(commentDto.getParent());
                    return commentService.update(com, id);
                }).orElseGet(() -> {
                    commentDto.setId(id);
                    return commentService.saveNew(CommentDto.toComment(commentDto));
                });
    }

    @GetMapping("/comment/{id}")
    public Comment one(@PathVariable int id) {
        return commentService.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }
}
