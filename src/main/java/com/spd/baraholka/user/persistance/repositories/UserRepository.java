package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.persistance.mappers.UserShortViewRowMapper;
import com.spd.baraholka.user.persistance.mappers.UserRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepository implements PersistenceUserService {

    private final NamedParameterJdbcTemplate paramJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;
    private final UserShortViewRowMapper userShortViewRowMapper;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate,
                          UserRowMapper userRowMapper,
                          UserShortViewRowMapper userShortViewRowMapper) {
    public UserRepository(NamedParameterJdbcTemplate paramJdbcTemplate, JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.paramJdbcTemplate = paramJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
        this.userShortViewRowMapper = userShortViewRowMapper;
    }

    @Override
    public Optional<User> selectUserById(int id) {
        String selectSQL = "SELECT * FROM users WHERE id=:id";
        Map<String, Integer> selectParameters = Map.of("id", id);
        try {
            return Optional.ofNullable(paramJdbcTemplate.queryForObject(selectSQL, selectParameters, userRowMapper));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> selectAllUsers() {
        String selectSQL = "SELECT id, first_name, last_name, is_blocked, end_date_of_ban FROM users";
        return jdbcTemplate.query(selectSQL, userShortViewRowMapper);
    }
}

    @Override
    public User create(User user) {
        final String sql = "INSERT INTO users (avatar, first_name, last_name, e_mail, location, phone_number, position) " +
                "VALUES (:avatar, :first_name, :last_name, :email, :location, :phone_number, :position) ";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("avatar", user.getAvatar())
                .addValue("first_name", user.getFirstName())
                .addValue("last_name", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("location", user.getLocation())
                .addValue("phone_number", user.getPhoneNumber())
                .addValue("position", user.getPosition());
        paramJdbcTemplate.update(sql, parameters, keyHolder);
        Map<String, Object> keys = Objects.requireNonNull(keyHolder.getKeys());
        if (keys.containsKey("id")) {
            Integer userId = (Integer) keys.get("id");
            user.setId(userId);
            saveUserRoles(user);
        }
        return user;
    }

    private void saveUserRoles(User user) {
        Set<Role> roles = Objects.requireNonNull(user.getRoles());
        int userId = user.getId();
        final String sql = "INSERT INTO users_roles (user_id, role) values (:user_id, :role)";
        for (Role role : roles) {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("user_id", userId)
                    .addValue("role", role.name());
            paramJdbcTemplate.update(sql, parameters);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return paramJdbcTemplate.queryForObject("SELECT count(*) <> 0 FROM users WHERE LOWER (e_mail) = LOWER (:email)",
                Map.of("email", email), Boolean.class);
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM users", Integer.class);
    }
}

