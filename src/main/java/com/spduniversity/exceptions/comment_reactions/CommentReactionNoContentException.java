package com.spduniversity.exceptions.comment_reactions;

public class CommentReactionNoContentException extends RuntimeException {

    public CommentReactionNoContentException() {
        super("Comment reaction has no content!");
    }
}
