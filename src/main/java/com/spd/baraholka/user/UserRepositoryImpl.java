package com.spd.baraholka.user;

import com.spd.baraholka.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate parameterizedJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(NamedParameterJdbcTemplate parameterizedJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.parameterizedJdbcTemplate = parameterizedJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User user) {
        final String sql = "INSERT INTO users (avatar, first_name, last_name, email, location, phone_number, position) " +
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
        parameterizedJdbcTemplate.update(sql, parameters, keyHolder);
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
            parameterizedJdbcTemplate.update(sql, parameters);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return parameterizedJdbcTemplate.queryForObject("SELECT count(*) <> 0 FROM users WHERE LOWER (email) = LOWER (:email)",
                Map.of("email", email), Boolean.class);
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM users", Integer.class);
    }
}
