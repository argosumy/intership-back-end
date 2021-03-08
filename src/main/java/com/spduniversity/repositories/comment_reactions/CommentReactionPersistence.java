package com.spduniversity.repositories.comment_reactions;

import com.spduniversity.entities.comments.CommentReaction;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface CommentReactionPersistence {

    int getTotalAmountByCommentId(int commentId);

    CommentReaction saveNew(CommentReaction commentReaction);

    void deleteLastRecord();
}
