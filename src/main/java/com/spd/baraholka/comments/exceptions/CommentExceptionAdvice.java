package com.spd.baraholka.comments.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class CommentExceptionAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CommentsNotFoundException.class)
    String commentsNotFoundHandler(CommentsNotFoundException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CommentNotFoundException.class)
    String commentNotFoundHandler(CommentNotFoundException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(CommentNoContentException.class)
    String commentNoContentHandler(CommentNoContentException e) {
        return e.getMessage();
    }
}
