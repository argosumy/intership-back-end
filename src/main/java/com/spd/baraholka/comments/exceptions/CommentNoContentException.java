package com.spd.baraholka.comments.exceptions;

public class CommentNoContentException extends RuntimeException {

    public CommentNoContentException() {
        super("Comment has no content!");
    }
}
