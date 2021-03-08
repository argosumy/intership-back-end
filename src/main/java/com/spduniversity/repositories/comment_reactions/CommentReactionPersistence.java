package com.spduniversity.repositories.comment_reactions;

import com.spduniversity.entities.comments.CommentReaction;

public interface CommentReactionPersistence {

    int getTotalReactionTypeByCommentId(int commentId, String commentReaction);

    CommentReaction saveNew(CommentReaction commentReaction);

    void deleteLastRecordByCommentId(int commentId, String commentReaction);
}
