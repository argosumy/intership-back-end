package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserBlockService;
import com.spd.baraholka.user.persistance.entities.BlockDetail;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BlockDetailRepository implements PersistenceUserBlockService {

    private static final String INSERT_SQL =
            "INSERT INTO users_block_details (user_id, baned_until, reason, is_notify) VALUES (:userId, :banedUntil, :reason, :isNotify) RETURNING id";
    private static final String IS_EXIST_SQL = "SELECT count(*) <> 0 FROM users_block_details WHERE user_id=:id";
    private static final String UPDATE_SQL = "UPDATE users_block_details SET baned_until=:banedUntil, reason=:reason, is_notify=:isNotify WHERE user_id=:userId";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BlockDetailRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertBlockDetails(BlockDetail blockDetail) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource insertParameters = createInsertParameters(blockDetail);
        jdbcTemplate.update(INSERT_SQL, insertParameters, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public Optional<Boolean> isUserAlreadyBlocked(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(IS_EXIST_SQL, Map.of("id", id), Boolean.class));
    }

    @Override
    public int updateBlockDetails(BlockDetail blockDetail) {
        Map<String, ? extends Comparable<? extends Comparable<?>>> updateParameters = createUpdateParameters(blockDetail);
        jdbcTemplate.update(UPDATE_SQL, updateParameters);
        return blockDetail.getUserId();
    }

    private Map<String, ? extends Comparable<? extends Comparable<?>>> createUpdateParameters(BlockDetail blockDetail) {
        return Map.of("banedUntil", blockDetail.getBanedUntil(),
                "reason", blockDetail.getReason(),
                "isNotify", blockDetail.isNotify(),
                "userId", blockDetail.getUserId());
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
