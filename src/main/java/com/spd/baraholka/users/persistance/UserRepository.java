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
        String selectSQL = createSelectUserByIDSQL();
        return jdbcTemplate.queryForObject(selectSQL, fillSelectUserByIdParameters(id), userRowMapper);
    }

    public int updateUserMainInfo(User user) {
        String updateSQL = createUpdateUserMainInfoSQL();
        Map<String, Object> updateParameters = createUpdateUserMainInfoParameters(user);
        jdbcTemplate.update(updateSQL, updateParameters);
        return user.getId();
    }

    private Map<String, Integer> fillSelectUserByIdParameters(int id) {
        return Map.of("id", id);
    }

    private String createSelectUserByIDSQL() {
        return "SELECT * FROM users WHERE id=:id";
    }

    private String createUpdateUserMainInfoSQL() {
        return "UPDATE users SET position=:position,"
                + " phone_number=:phoneNumber,"
                + " additional_contact_resources=:additionalContactResources"
                + " WHERE id=:id";
    }

    private Map<String, Object> createUpdateUserMainInfoParameters(User user) {
        String additionalContactResources = createJsonString(user.getAdditionalContactResources());
        return Map.of("position", user.getPosition(),
                "phoneNumber", user.getPhoneNumber(),
                "additionalContactResources", additionalContactResources,
                "id", user.getId());
    }

    private String createJsonString(Map<String, String> additionalContactResources) {
        JSONObject jsonObject = new JSONObject(additionalContactResources);
        return jsonObject.toJSONString();
    }
}
