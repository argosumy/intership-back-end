package com.spd.baraholka.users.persistance;

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
        return jdbcTemplate.queryForObject(selectSQL, Map.of("id", id), userRowMapper);
    }

    public int updateUserMainInfo(User user) {
        String updateSQL = createUpdateUserMainInfoSQL();
        jdbcTemplate.update(updateSQL, fillUpdateUserMainInfoParameters(user));
        return user.getId();
    }

    private String createUpdateUserMainInfoSQL() {
        return "UPDATE users SET position=:position,"
                + " phone_number=:phoneNumber,"
                + " additional_contact_resources=:additionalContactResources"
                + " WHERE id=:id";
    }

    private Map<String, Object> fillUpdateUserMainInfoParameters(User user) {
        return Map.of("position", user.getPosition(),
                "phone_number", user.getPhoneNumber(),
                "additional_contact_resources", user.getAdditionalContactResources(),
                "id", user.getId());
    }
}
