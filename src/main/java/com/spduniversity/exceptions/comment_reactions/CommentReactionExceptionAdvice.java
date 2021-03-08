package com.spduniversity.exceptions.comment_reactions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CommentReactionExceptionAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(CommentReactionNoContentException.class)
    String CommentReactionNoContentHandler(CommentReactionNoContentException e) {
        return e.getMessage();
    }
}
