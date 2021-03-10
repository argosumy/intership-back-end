package com.spd.baraholka.comment_reactions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentReactionExceptionAdvice {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(CommentReactionNoContentException.class)
    String commentReactionNoContentHandler(CommentReactionNoContentException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CommentReactionBadRequestException.class)
    String commentReactionNoContentHandler(CommentReactionBadRequestException e) {
        return e.getMessage();
    }
}
