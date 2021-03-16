package com.spd.baraholka.user.persistance;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
        JSONObject additionalContactResources = getAdditionalContactResources(rs);
        user.setAdditionalContactResources(additionalContactResources);
        return user;
    }

    private JSONObject getAdditionalContactResources(ResultSet resultSet) throws SQLException {
        String jsonString = resultSet.getString("additional_contact_resources");
        JSONParser parser = new JSONParser();
        JSONObject additionalContactResources = new JSONObject();
        try {
            additionalContactResources = (JSONObject) parser.parse(jsonString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return additionalContactResources;
    }
}