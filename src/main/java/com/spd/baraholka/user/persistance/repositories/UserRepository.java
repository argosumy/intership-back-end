package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.mappers.UserRowMapper;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.persistance.mappers.UserShortViewRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository implements PersistenceUserService {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;
    private final UserShortViewRowMapper userShortViewRowMapper;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate,
                          UserRowMapper userRowMapper,
                          UserShortViewRowMapper userShortViewRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
        this.userShortViewRowMapper = userShortViewRowMapper;
    }

    @Override
    public User selectUserById(int id) {
        String selectSQL = "SELECT * FROM users WHERE id=:id";
        Map<String, Integer> selectParameters = Map.of("id", id);
        return jdbcTemplate.queryForObject(selectSQL, selectParameters, userRowMapper);
    }

    @Override
    public List<User> selectAllUsers() {
        String selectSQL = "SELECT id, first_name, last_name, is_blocked, end_date_of_ban FROM users";
        return jdbcTemplate.query(selectSQL, userShortViewRowMapper);
    }
}
