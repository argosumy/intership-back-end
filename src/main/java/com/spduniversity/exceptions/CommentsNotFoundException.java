package com.spduniversity.exceptions;

public class CommentsNotFoundException extends RuntimeException {

    public CommentsNotFoundException() {
        super("No comments yet!");
    }
}
