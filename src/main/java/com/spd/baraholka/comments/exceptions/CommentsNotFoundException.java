package com.spd.baraholka.comments.exceptions;

public class CommentsNotFoundException extends RuntimeException {

    public CommentsNotFoundException() {
        super("No comments yet!");
    }
}
