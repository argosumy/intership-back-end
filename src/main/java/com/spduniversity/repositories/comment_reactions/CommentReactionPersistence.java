package com.spduniversity.repositories.comment_reactions;

import com.spduniversity.entities.comment_reactions.CommentReaction;

public interface CommentReactionPersistence {

    int getTotalReactionTypeByCommentId(int commentId, String commentReaction);

    CommentReaction saveNew(CommentReaction commentReaction);

    void deleteLastRecordByCommentId(int commentId, String commentReaction);
}
