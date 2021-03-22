package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserAdditionalResourcesService;
import com.spd.baraholka.user.persistance.entities.UserAdditionalResource;
import com.spd.baraholka.user.persistance.mappers.UserAdditionalResourceRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
}
