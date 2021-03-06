package com.spduniversity.exceptions;

public class CommentNotFoundException extends  RuntimeException {

    public CommentNotFoundException() {
        super("No comments yet!");
    }
}
