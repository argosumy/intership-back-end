package com.spd.baraholka.comment_reactions.exceptions;

public class CommentReactionNoContentException extends RuntimeException {

    public CommentReactionNoContentException() {
        super("Comment reaction has no content!");
    }
}
