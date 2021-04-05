package com.spd.baraholka.comment_reactions.exceptions;

public class CommentReactionBadRequestException extends RuntimeException {

    public CommentReactionBadRequestException() {
        super("Bad request for getting comment reactions!");
    }
}

