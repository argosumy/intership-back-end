package com.spduniversity.entities.comments;

import com.spduniversity.entities.enums.CommentReactionType;
import com.spduniversity.entities.users.User;

public class CommentReaction {

    private int id;
    private CommentReactionType commentReaction;
    private Comment comment;
    private User user;
}
