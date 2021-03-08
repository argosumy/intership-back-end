package com.spduniversity.exceptions.comment_reactions;

public class CommentReactionBadRequestException extends RuntimeException {

    public CommentReactionBadRequestException() {
        super("Bad request for getting comment reactions!");
    }
}

