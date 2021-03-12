package com.spd.baraholka.users.persistance;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

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
        Map<String, String> additionalContactResources = getAdditionalContactResources(rs);
        user.setAdditionalContactResources(additionalContactResources);
        return user;
    }

    private Map<String, String> getAdditionalContactResources(ResultSet resultSet) throws SQLException {
        Array resultSetArray = resultSet.getArray("additional_contact_resources");
        String[][] additionalContactResources = (String[][]) resultSetArray.getArray();
        return Arrays.stream(additionalContactResources).collect(Collectors.toMap(key -> key[0], value -> value[1]));
    }
}