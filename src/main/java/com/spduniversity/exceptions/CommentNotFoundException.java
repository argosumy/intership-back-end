package com.spduniversity.exceptions;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(int id) {
        super("Could not find comment " + id);
    }
}
