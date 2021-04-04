package com.spd.baraholka.comments.repositories;

import com.spd.baraholka.comments.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentPersistence {

    List<Comment> getAllByAdId(int adId);

    int saveNew(Comment comment);

    Comment update(Comment comment, int id);

    void deleteById(int id);

    Optional<Comment> findById(int id);
}
