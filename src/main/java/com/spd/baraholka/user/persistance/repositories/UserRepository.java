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

    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM users WHERE id=:id";
    private static final String SELECT_ALL_USERS_SQL = "SELECT id, first_name, last_name, avatar, e_mail FROM users";
    private static final String EXIST_BY_EMAIL_SQL = "SELECT count(*) <> 0 FROM users WHERE LOWER (e_mail) = LOWER (:email)";
    private static final String USER_COUNT_SQL = "SELECT count(*) FROM users";
    private static final String EXIST_BY_ID_SQL = "SELECT count(*) <> 0 FROM users WHERE id=:id";
    private static final String SELECT_OWNER_SQL = "SELECT id, first_name, last_name, avatar, e_mail FROM users WHERE id=:id";
    private static final String SAVE_USER_ROLE_SQL = "INSERT INTO users_roles (user_id, role) values (:user_id, :role)";
    private static final String INSERT_USER_SQL = "INSERT INTO users (first_name, last_name, e_mail, location, phone_number, position, avatar) " +
            "VALUES (:first_name, :last_name, :email, :location, :phone_number, :position, :avatar) ";
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
        return jdbcTemplate.queryForObject(SELECT_USER_BY_ID_SQL, Map.of("id", id), userRowMapper);
    }

    @Override
    public List<User> selectAllUsers() {
        return jdbcTemplate.query(SELECT_ALL_USERS_SQL, userShortViewRowMapper);
    }

    @Override
    public Optional<Boolean> existsByEmail(String email) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(EXIST_BY_EMAIL_SQL, Map.of("email", email), Boolean.class));
    }

    @Override
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = createInsertUserParameters(user);
        jdbcTemplate.update(INSERT_USER_SQL, parameters, keyHolder);
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
        return Optional.ofNullable(jdbcTemplate.queryForObject(USER_COUNT_SQL, Map.of(), Integer.class));
    }

    @Override
    public Optional<Boolean> isExist(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(EXIST_BY_ID_SQL, Map.of("id", id), Boolean.class));
    }

    @Override
    public Optional<Owner> selectOwner(int id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_OWNER_SQL, Map.of("id", id), ownerRowMapper));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    private MapSqlParameterSource createInsertUserParameters(User user) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("first_name", user.getFirstName());
        namedParameters.addValue("last_name", user.getLastName());
        namedParameters.addValue("email", user.getEmail());
        namedParameters.addValue("location", user.getLocation());
        namedParameters.addValue("phone_number", user.getPhoneNumber());
        namedParameters.addValue("position", user.getPosition());
        namedParameters.addValue("avatar", user.getImageUrl());
        return namedParameters;
    }

    private void saveUserRoles(User user) {
        Set<Role> roles = Objects.requireNonNull(user.getRoles());
        int userId = user.getId();
        for (Role role : roles) {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("user_id", userId)
                    .addValue("role", role.name());
            jdbcTemplate.update(SAVE_USER_ROLE_SQL, parameters);
        }
    }
}

