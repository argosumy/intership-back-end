package com.spd.baraholka.comments.exceptions;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(int id) {
        super("Could not find comment " + id);
    }
}
