package com.spd.baraholka.users.persistance;

import org.json.simple.JSONObject;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    public User selectUserById(int id) {
        String selectSQL = "SELECT * FROM users WHERE id=:id";
        Map<String, Integer> updateParameters = Map.of("id", id);
        return jdbcTemplate.queryForObject(selectSQL, updateParameters, userRowMapper);
    }

    public int updateUserMainInfo(User user) {
        String updateSQL = createUpdateUserMainInfoSQL();
        Map<String, Object> updateParameters = createUpdateUserMainInfoParameters(user);
        jdbcTemplate.update(updateSQL, updateParameters);
        return user.getId();
    }

    private String createUpdateUserMainInfoSQL() {
        return "UPDATE users SET position=:position,"
                + " phone_number=:phoneNumber,"
                + " additional_contact_resources=:additionalContactResources"
                + " WHERE id=:id";
    }

    private Map<String, Object> createUpdateUserMainInfoParameters(User user) {
        JSONObject additionalContactResources = user.getAdditionalContactResources();
        return Map.of("position", user.getPosition(),
                "phoneNumber", user.getPhoneNumber(),
                "additionalContactResources", additionalContactResources.toJSONString(),
                "id", user.getId());
    }
}
