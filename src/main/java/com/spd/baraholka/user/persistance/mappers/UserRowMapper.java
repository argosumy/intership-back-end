package com.spd.baraholka.user.persistance.mappers;

import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
        user.setLocation(rs.getString("location"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setBlocked(rs.getBoolean("is_blocked"));
        user.setImageUrl(rs.getString("avatar"));
        setUserEndDateOfBan(user, rs);
        user.setLocation(rs.getString("location"));
        return user;
    }

    private void setUserEndDateOfBan(User user, ResultSet resultSet) throws SQLException {
        Timestamp endDateOfBan = resultSet.getTimestamp("end_date_of_ban");
        if (endDateOfBan != null) {
            user.setEndDateOfBan(endDateOfBan.toLocalDateTime());
        }
    }
}
