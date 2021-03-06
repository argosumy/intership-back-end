package com.spduniversity.repositories;

import com.spduniversity.entities.comments.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentPersistence {

    List<Comment> getAllByAdId(int adId);

    Comment saveNew(Comment comment);

    Comment update(Comment comment, int id);

    void deleteById(int id);

    Optional<Comment> findById(int id);
}
