package com.spduniversity.exceptions;

public class CommentNoContentException  extends  RuntimeException {

    public CommentNoContentException() {
        super("Comment has no content!");
    }
}
