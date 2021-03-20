package com.spd.baraholka.comments.controllers;

import com.spd.baraholka.comments.dto.CommentDto;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.config.exceptions.*;
import com.spd.baraholka.comments.mappers.CommentDtoMapper;
import com.spd.baraholka.comments.services.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentDtoMapper commentDtoMapper;

    public CommentController(CommentService commentService, CommentDtoMapper commentDtoMapper) {
        this.commentService = commentService;
        this.commentDtoMapper = commentDtoMapper;
    }

    @GetMapping("/comments/{adId}")
    public List<CommentDto> getCommentsByAdId(@PathVariable("adId") int adId) {
        List<Comment> commentList = commentService.getAllByAdId(adId);
        return commentDtoMapper.toCommentDtoList(commentList);
    }

    @GetMapping("/comments/limit/{adId}")
    public List<CommentDto> getLimitCommentsByAdId(@PathVariable("adId") int adId) {
        List<Comment> commentList = commentService.getLimitCommentsByAdId(adId);
        return commentDtoMapper.toCommentDtoList(commentList);
    }

    @PostMapping("/comments")
    public Comment saveComment(@RequestBody @Valid CommentDto commentDto) {
        Comment comment = commentDtoMapper.toComment(commentDto);
        return commentService.saveNew(comment);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable("id") int id) {
        commentService.deleteById(id);
    }

    @PutMapping("/comment/{id}")
    public CommentDto updateComment(@RequestBody @Valid CommentDto commentDto, @PathVariable("id") int id) {
        Comment comment = commentService.findById(id).orElseThrow(NotFoundException::new);
        Comment commentToUpdate = commentDtoMapper.updateExistsComment(comment, commentDto);
        return commentDtoMapper.getCommentDto(commentService.update(commentToUpdate, id));
    }

    @GetMapping("/comment/{id}")
    public CommentDto getOneComment(@PathVariable("id") int id) {
        Comment comment = commentService.findById(id).orElseThrow(NotFoundException::new);
        return commentDtoMapper.getCommentDto(comment);
    }
}
