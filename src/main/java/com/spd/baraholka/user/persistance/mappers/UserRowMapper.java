package com.spd.baraholka.user.persistance.mappers;

import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("e_mail"));
        user.setPosition(rs.getString("position"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setBlocked(rs.getBoolean("is_blocked"));
        user.setImageUrl(rs.getString("image_url"));
        return user;
    }
}
