package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserAdditionalResourcesService;
import com.spd.baraholka.user.persistance.entities.UserAdditionalResource;
import com.spd.baraholka.user.persistance.mappers.UserAdditionalResourceRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserAdditionalResourceRepository implements PersistenceUserAdditionalResourcesService {

    private static final String INSERT_USER_ADDITIONAL_RESOURCE_SQL =
            "INSERT INTO users_additional_resources (user_id, resource_name, resource_url) VALUES (:userId, :resourceName, :resourceUrl)";
    private static final String SELECT_USER_ADDITIONAL_RESOURCES_ID = "SELECT id FROM users_additional_resources WHERE user_id=:userId";
    private static final String SELECT_USER_ADDITIONAL_RESOURCES_SQL = "SELECT * FROM users_additional_resources WHERE user_id=:id";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserAdditionalResourceRowMapper resourceRowMapper;

    public UserAdditionalResourceRepository(NamedParameterJdbcTemplate jdbcTemplate,
                                            UserAdditionalResourceRowMapper resourceRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceRowMapper = resourceRowMapper;
    }

    @Override
    public List<UserAdditionalResource> selectUserAdditionalResources(int id) {
        return jdbcTemplate.query(SELECT_USER_ADDITIONAL_RESOURCES_SQL, Map.of("id", id), resourceRowMapper);
    }

    @Override
    public void updateUserAdditionalResources(List<UserAdditionalResource> additionalResources) {
        String updateSQL = "UPDATE users_additional_resources SET resource_name=:resourceName, resource_url=:resourceUrl WHERE id=:id";
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(additionalResources.toArray());
        jdbcTemplate.batchUpdate(updateSQL, batch);
    }

    @Override
    public void insertNewUserAdditionalResources(List<UserAdditionalResource> additionalResources) {
        SqlParameterSource[] batchParameters = SqlParameterSourceUtils.createBatch(additionalResources.toArray());
        jdbcTemplate.batchUpdate(INSERT_USER_ADDITIONAL_RESOURCE_SQL, batchParameters);
    }

    @Override
    public List<Integer> selectUserAdditionalResourcesId(int userId) {
        return jdbcTemplate.queryForList(SELECT_USER_ADDITIONAL_RESOURCES_ID, Map.of("userId", userId), Integer.class);
    }
}
