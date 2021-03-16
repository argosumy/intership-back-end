package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.mappers.UserRowMapper;
import com.spd.baraholka.user.persistance.entities.User;
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
    public User selectUserById(int id) {
        String selectSQL = "SELECT * FROM users WHERE id=:id";
        Map<String, Integer> selectParameters = Map.of("id", id);
        return jdbcTemplate.queryForObject(selectSQL, selectParameters, userRowMapper);
    }

    @Override
    public int updateUserBlockedStatus(int id, boolean isBlocked) {
        String updateSQL = "UPDATE users SET is_blocked=:isBlocked WHERE id=:id";
        Map<String, Object> updateParameters = Map.of("isBlocked", isBlocked, "id", id);
        jdbcTemplate.update(updateSQL, updateParameters);
        return id;
    }
}
