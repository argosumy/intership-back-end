package com.spduniversity.exceptions.comments;

public class CommentsNotFoundException extends RuntimeException {

    public CommentsNotFoundException() {
        super("No comments yet!");
    }
}
