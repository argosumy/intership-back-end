package com.spduniversity.exceptions.comments;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(int id) {
        super("Could not find comment " + id);
    }
}
