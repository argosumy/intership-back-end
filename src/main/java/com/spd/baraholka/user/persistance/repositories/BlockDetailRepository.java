package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserBlockService;
import com.spd.baraholka.user.persistance.entities.BlockDetail;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Objects;

@Repository
public class BlockDetailRepository implements PersistenceUserBlockService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BlockDetailRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertBlockDetails(BlockDetail blockDetail) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSQL = "INSERT INTO users_block_details (user_id, baned_until, reason, is_notify) VALUES (:userId, :banedUntil, :reason, :isNotify) RETURNING id";
        MapSqlParameterSource insertParameters = createInsertParameters(blockDetail);
        jdbcTemplate.update(insertSQL, insertParameters, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    private MapSqlParameterSource createInsertParameters(BlockDetail blockDetailDTO) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("userId", blockDetailDTO.getUserId());
        namedParameters.addValue("banedUntil", Timestamp.valueOf(blockDetailDTO.getBanedUntil()));
        namedParameters.addValue("reason", blockDetailDTO.getReason());
        namedParameters.addValue("isNotify", blockDetailDTO.isNotify());
        return namedParameters;
    }
}
