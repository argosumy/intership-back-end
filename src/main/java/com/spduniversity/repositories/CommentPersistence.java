package com.spduniversity.repositories;

import com.spduniversity.entities.comments.Comment;

import java.util.List;

public interface CommentPersistence {

    List<Comment> getAllByAdId(int adId);

    Comment saveNew(Comment comment);

    void remove(int commentId);

    void update(Comment comment);
}
