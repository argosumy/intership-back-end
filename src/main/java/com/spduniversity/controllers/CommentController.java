package com.spduniversity.controllers;

import com.spduniversity.dto.CommentDto;
import com.spduniversity.entities.comments.Comment;
import com.spduniversity.exceptions.CommentNoContentException;
import com.spduniversity.exceptions.CommentNotFoundException;
import com.spduniversity.exceptions.CommentsNotFoundException;
import com.spduniversity.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments/{adId}")
    List<CommentDto> getCommentsByAdId(@PathVariable("adId") int adId) {
        List<Comment> commentList = commentService.getAllByAdId(adId);

        if (commentList.isEmpty()) {
            throw new CommentsNotFoundException();
        }

        return CommentDto.toCommentDtoList(commentList);
    }

    @PostMapping("/comments")
    Comment saveComment(@RequestBody CommentDto commentDto) {
        if (commentDto == null) {
            throw new CommentNoContentException();
        }
        Comment comment = CommentDto.toComment(commentDto);
        return commentService.saveNew(comment);
    }

    @DeleteMapping("/comments/{id}")
    void deleteComment(@PathVariable int id) {
        commentService.deleteById(id);
    }

    @PutMapping("/comments/{id}")
    Comment updateComment(@RequestBody CommentDto commentDto, @PathVariable int id) {
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

    @GetMapping("/comments/one/{id}")
    Comment one(@PathVariable int id) {
        return commentService.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }
}
