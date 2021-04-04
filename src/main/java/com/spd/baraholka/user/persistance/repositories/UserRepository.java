package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.entities.Owner;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.persistance.mappers.OwnerRowMapper;
import com.spd.baraholka.user.persistance.mappers.UserShortViewRowMapper;
import com.spd.baraholka.user.persistance.mappers.UserRowMapper;
import org.springframework.dao.DataAccessException;
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

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;
    private final UserShortViewRowMapper userShortViewRowMapper;
    private final OwnerRowMapper ownerRowMapper;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate,
                          UserRowMapper userRowMapper,
                          UserShortViewRowMapper userShortViewRowMapper,
                          OwnerRowMapper ownerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
        this.userShortViewRowMapper = userShortViewRowMapper;
        this.ownerRowMapper = ownerRowMapper;
    }

    @Override
    public User selectUserById(int id) {
        String selectSQL = "SELECT * FROM users WHERE id=:id";
        Map<String, Integer> selectParameters = Map.of("id", id);
        return jdbcTemplate.queryForObject(selectSQL, selectParameters, userRowMapper);
    }

    @Override
    public List<User> selectAllUsers() {
        String selectSQL = "SELECT id, first_name, last_name, is_blocked, end_date_of_ban, avatar, e_mail FROM users";
        return jdbcTemplate.query(selectSQL, userShortViewRowMapper);
    }

    @Override
    public Optional<Boolean> existsByEmail(String email) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT count(*) <> 0 FROM users WHERE LOWER (e_mail) = LOWER (:email)",
                Map.of("email", email), Boolean.class));
    }

    @Override
    public User create(User user) {
        final String sql = "INSERT INTO users (first_name, last_name, e_mail, location, phone_number, position, avatar) " +
                "VALUES (:first_name, :last_name, :email, :location, :phone_number, :position, :avatar) ";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("first_name", user.getFirstName())
                .addValue("last_name", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("location", user.getLocation())
                .addValue("phone_number", user.getPhoneNumber())
                .addValue("position", user.getPosition())
                .addValue("avatar", user.getImageUrl());
        jdbcTemplate.update(sql, parameters, keyHolder);
        Map<String, Object> keys = Objects.requireNonNull(keyHolder.getKeys());
        if (keys.containsKey("id")) {
            Integer userId = (Integer) keys.get("id");
            user.setId(userId);
            saveUserRoles(user);
        }
        return user;
    }

    @Override
    public Optional<Integer> count() {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT count(*) FROM users", Map.of(), Integer.class));
    }

    @Override
    public Optional<Boolean> isExist(int id) {
        String isExistQuery = "SELECT count(*) <> 0 FROM users WHERE id=:id";
        return Optional.ofNullable(jdbcTemplate.queryForObject(isExistQuery, Map.of("id", id), Boolean.class));
    }

    @Override
    public Optional<Owner> selectOwner(int id) {
        String selectSQL = "SELECT id, first_name, last_name, avatar, e_mail FROM users WHERE id=:id";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(selectSQL, Map.of("id", id), ownerRowMapper));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    private void saveUserRoles(User user) {
        Set<Role> roles = Objects.requireNonNull(user.getRoles());
        int userId = user.getId();
        final String sql = "INSERT INTO users_roles (user_id, role) values (:user_id, :role)";
        for (Role role : roles) {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("user_id", userId)
                    .addValue("role", role.name());
            jdbcTemplate.update(sql, parameters);
        }
    }

    @Override
    public int updateAvatar(int userId, String imageUrl) {
        final String sql = "UPDATE users SET avatar = :avatar WHERE id = :user_id";
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("avatar", imageUrl);
        return jdbcTemplate.update(sql, parameters);
    }
}

