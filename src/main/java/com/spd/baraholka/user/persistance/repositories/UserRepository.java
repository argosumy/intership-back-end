package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.persistance.mappers.UserRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository implements PersistenceUserService {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public Optional<User> selectUserById(int id) {
        String selectSQL = "SELECT * FROM users WHERE id=:id";
        Map<String, Integer> selectParameters = Map.of("id", id);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(selectSQL, selectParameters, userRowMapper));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }
}
