package com.spd.baraholka.comments.mappers;

import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setBody(rs.getString("body"));
        comment.setCreatedDate(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
        comment.setAdvertisement(getMappedAdvertisement(rs));
        comment.setUser(getMappedUser(rs));
        comment.setParent(getMappedParentComment(rs));
        return comment;
    }

    private Comment getMappedParentComment(ResultSet rs) throws SQLException {
        Comment parent = new Comment();
        parent.setId(rs.getInt("parent_id"));
        return parent;
    }

    private Advertisement getMappedAdvertisement(ResultSet rs) throws SQLException {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementId(rs.getInt("ad_id"));
        return advertisement;
    }

    private User getMappedUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        return user;
    }
}
