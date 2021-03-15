package com.spd.baraholka.user.persistance;

import org.json.simple.JSONObject;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepository implements PersistenceUserService {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public User getUserById(int id) {
        String selectSQL = "SELECT * FROM users WHERE id=:id";
        Map<String, Integer> selectParameters = Map.of("id", id);
        return jdbcTemplate.queryForObject(selectSQL, selectParameters, userRowMapper);
    }

    @Override
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
