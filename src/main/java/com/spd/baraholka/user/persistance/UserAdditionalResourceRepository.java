package com.spd.baraholka.user.persistance;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserAdditionalResourceRepository implements PersistenceUserAdditionalResourcesService {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserAdditionalResourceRowMapper resourceRowMapper;

    public UserAdditionalResourceRepository(NamedParameterJdbcTemplate jdbcTemplate,
                                            UserAdditionalResourceRowMapper resourceRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceRowMapper = resourceRowMapper;
    }

    @Override
    public List<UserAdditionalResource> selectUserAdditionalResources(int id) {
        String selectSQL = "SELECT * FROM users_additional_resources WHERE user_id=:id";
        Map<String, Integer> selectParameters = Map.of("id", id);
        return jdbcTemplate.query(selectSQL, selectParameters, resourceRowMapper);
    }

    @Override
    public void updateUserAdditionalResources(List<UserAdditionalResource> additionalResources) {
        String updateSQL = "UPDATE users_additional_resources SET resource_name=:resourceName, resource_url=:resourceUrl WHERE id=:id";
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(additionalResources.toArray());
        jdbcTemplate.batchUpdate(updateSQL, batch);
    }
}
