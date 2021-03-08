package com.spduniversity.mappers;

import com.spduniversity.entities.advertisements.Advertisement;
import com.spduniversity.entities.comments.Comment;
import com.spduniversity.entities.users.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {

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
        advertisement.setId(rs.getInt("ad_id"));
        return advertisement;
    }

    private User getMappedUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setAvatar(rs.getString("avatar"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPosition(rs.getString("position"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setBlockedStatus(rs.getBoolean("blocked_status"));
        user.setResourcesLink(rs.getString("resources_link"));
        return user;
    }
}
