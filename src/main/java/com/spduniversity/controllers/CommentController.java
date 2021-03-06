package com.spduniversity.controllers;

import com.spduniversity.entities.comments.Comment;
import com.spduniversity.exceptions.CommentNoContentException;
import com.spduniversity.exceptions.CommentNotFoundException;
import com.spduniversity.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping()
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    List<Comment> getCommentsByAdId(@RequestParam int adId) {
        List<Comment> commentList = commentService.getAllByAdId(adId);

        if (commentList.isEmpty()) {
            throw new CommentNotFoundException();
        }

        return commentList;
    }

    @PostMapping
    Comment saveComment(@RequestBody Comment comment) {
        if (comment == null) {
            throw new CommentNoContentException();
        }
        return commentService.saveNew(comment);
    }


}
