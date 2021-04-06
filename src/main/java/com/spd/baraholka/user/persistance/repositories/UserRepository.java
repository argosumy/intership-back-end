package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.entities.Owner;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.persistance.mappers.OwnerRowMapper;
import com.spd.baraholka.user.persistance.mappers.UserMainInfoRowMapper;
import com.spd.baraholka.user.persistance.mappers.UserShortViewRowMapper;
import com.spd.baraholka.user.persistance.mappers.UserRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

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
    private static final String SELECT_USER_MAIN_INFO = "SELECT id, position, phone_number, location FROM users WHERE id=:userId";
    private static final String UPDATE_USER_MAIN_INFO_SQL = "UPDATE users SET position=:position, phone_number=:phoneNumber, location=:location WHERE id=:id";
    private static final String SELECT_USER_BY_EMAIL_SQL = "SELECT * FROM users WHERE LOWER(e_mail) = LOWER(:email)";
    private static final String SELECT_ROLES_BY_USER_SQL = "SELECT role FROM users_roles WHERE user_id = :user_id";
    private static final String UPDATE_USER_AVATAR_SQL = "UPDATE users SET avatar = :avatar WHERE id = :user_id";

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;
    private final UserShortViewRowMapper userShortViewRowMapper;
    private final OwnerRowMapper ownerRowMapper;
    private final UserMainInfoRowMapper userMainInfoRowMapper;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate,
                          UserRowMapper userRowMapper,
                          UserShortViewRowMapper userShortViewRowMapper,
                          OwnerRowMapper ownerRowMapper,
                          UserMainInfoRowMapper userMainInfoRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
        this.userShortViewRowMapper = userShortViewRowMapper;
        this.ownerRowMapper = ownerRowMapper;
        this.userMainInfoRowMapper = userMainInfoRowMapper;
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
    public User updateUserMainInfo(User user) {
        MapSqlParameterSource updateParameters = createUpdateUserMainInfoParameters(user);
        jdbcTemplate.update(UPDATE_USER_MAIN_INFO_SQL, updateParameters);
        return selectUserMainInfo(user.getId());
    }

    @Override
    public User selectUserMainInfo(int userId) {
        return jdbcTemplate.queryForObject(SELECT_USER_MAIN_INFO, Map.of("userId", userId), userMainInfoRowMapper);
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

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate
                    .queryForObject(SELECT_USER_BY_EMAIL_SQL,
                            Map.of("email", email),
                            userRowMapper)
            );
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Set<Role> getRolesByUserId(int id) {
        Set<Role> roles = new HashSet<>();
        List<String> roleNames = jdbcTemplate.queryForList(SELECT_ROLES_BY_USER_SQL,
                Map.of("user_id", id),
                String.class);
        for (String role : roleNames) {
            roles.add(Role.valueOf(role));
        }
        return roles;
    }

    private MapSqlParameterSource createUpdateUserMainInfoParameters(User user) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("position", user.getPosition());
        namedParameters.addValue("phoneNumber", user.getPhoneNumber());
        namedParameters.addValue("location", user.getLocation());
        namedParameters.addValue("id", user.getId());
        return namedParameters;
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

    @Override
    public int updateAvatar(int userId, String imageUrl) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("avatar", imageUrl);
        return jdbcTemplate.update(UPDATE_USER_AVATAR_SQL, parameters);
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
