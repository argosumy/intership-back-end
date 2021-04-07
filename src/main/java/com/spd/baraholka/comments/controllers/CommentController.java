package com.spd.baraholka.comments.controllers;

import com.spd.baraholka.comments.dto.CommentDto;
import com.spd.baraholka.comments.dto.CommentUserInfoDto;
import com.spd.baraholka.comments.dto.UpdatedCommentDto;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.comments.exceptions.CommentNotFoundException;
import com.spd.baraholka.comments.mappers.CommentDtoMapper;
import com.spd.baraholka.comments.mappers.CommentUserInfoDtoMapper;
import com.spd.baraholka.comments.services.CommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentDtoMapper commentDtoMapper;
    private final CommentUserInfoDtoMapper commentUserInfoDtoMapper;

    public CommentController(CommentService commentService, CommentDtoMapper commentDtoMapper,
                             CommentUserInfoDtoMapper commentUserInfoDtoMapper) {
        this.commentService = commentService;
        this.commentDtoMapper = commentDtoMapper;
        this.commentUserInfoDtoMapper = commentUserInfoDtoMapper;
    }

    @GetMapping("/comments/{adId}")
    public List<CommentUserInfoDto> getCommentsByAdId(@PathVariable("adId") int adId) {
        List<Comment> commentList = commentService.getAllByAdId(adId);
        return commentUserInfoDtoMapper.toCommentUserInfoDtoList(commentList);
    }

    @GetMapping("/comments/limit/{adId}")
    public List<CommentUserInfoDto> getLimitCommentsByAdId(@PathVariable("adId") int adId) {
        List<Comment> commentList = commentService.getLimitCommentsByAdId(adId);
        return commentUserInfoDtoMapper.toCommentUserInfoDtoList(commentList);
    }

    @PostMapping("/comments")
    public int saveComment(@RequestBody @Valid CommentDto commentDto) {
        Comment comment = commentDtoMapper.toComment(commentDto);
        return commentService.saveNew(comment);
    }

    @PreAuthorize("hasAnyAuthority('MODERATOR')")
    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable("id") int id) {
        commentService.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        commentService.deleteById(id);
    }

    @PreAuthorize("hasAnyAuthority('MODERATOR')")
    @PutMapping("/comment/{id}")
    public CommentDto updateComment(@RequestBody @Valid UpdatedCommentDto updatedCommentDto, @PathVariable("id") int id) {
        Comment comment = commentService.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        Comment commentToUpdate = commentDtoMapper.updateExistsComment(comment, updatedCommentDto);
        return commentDtoMapper.getCommentDto(commentService.update(commentToUpdate, id));
    }

    @GetMapping("/comment/{id}")
    public CommentUserInfoDto getOneComment(@PathVariable("id") int id) {
        Comment comment = commentService.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        return commentUserInfoDtoMapper.getCommentUserInfoDto(comment);
    }
}
