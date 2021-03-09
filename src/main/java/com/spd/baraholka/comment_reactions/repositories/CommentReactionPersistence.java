package com.spd.baraholka.comment_reactions.repositories;

import com.spd.baraholka.comment_reactions.entities.CommentReaction;

public interface CommentReactionPersistence {

    int getTotalReactionTypeByCommentId(int commentId, String commentReaction);

    CommentReaction saveNew(CommentReaction commentReaction);

    void deleteLastRecordByCommentId(int commentId, String commentReaction);
}
