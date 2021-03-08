package com.spduniversity.exceptions.comments;

public class CommentNoContentException extends RuntimeException {

    public CommentNoContentException() {
        super("Comment has no content!");
    }
}
