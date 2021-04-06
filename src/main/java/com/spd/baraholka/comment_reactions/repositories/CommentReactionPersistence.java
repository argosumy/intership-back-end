package com.spd.baraholka.comment_reactions.repositories;

import com.spd.baraholka.comment_reactions.entities.CommentReaction;
import com.spd.baraholka.comment_reactions.enums.CommentReactionType;

public interface CommentReactionPersistence {

    int getTotalReactionTypeByCommentId(int commentId, CommentReactionType commentReactionType);

    CommentReaction saveNew(CommentReaction commentReaction);

    void deleteLastRecordByCommentId(int commentId, CommentReactionType commentReactionType);
}
