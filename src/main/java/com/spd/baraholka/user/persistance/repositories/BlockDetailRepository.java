package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserBlockService;
import com.spd.baraholka.user.persistance.entities.BlockDetail;
import com.spd.baraholka.user.persistance.mappers.BlockDetailsRowMapper;
import org.springframework.dao.DataAccessException;
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
            "INSERT INTO users_block_details (user_id, blocked_until, reason, is_notify) VALUES (:userId, :blockedUntil, :reason, :isNotify) RETURNING id";
    private static final String IS_EXIST_SQL = "SELECT count(*) <> 0 FROM users_block_details WHERE user_id=:id";
    private static final String UPDATE_SQL = "UPDATE users_block_details SET blocked_until=:blockedUntil, reason=:reason, is_notify=:isNotify WHERE user_id=:userId";
    private static final String SHORT_VIEW_SELECT_SQL = "SELECT is_blocked, blocked_until FROM users_block_details WHERE user_id=:userId";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final BlockDetailsRowMapper blockDetailsRowMapper;

    public BlockDetailRepository(NamedParameterJdbcTemplate jdbcTemplate, BlockDetailsRowMapper blockDetailsRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.blockDetailsRowMapper = blockDetailsRowMapper;
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

    @Override
    public Optional<BlockDetail> selectShortBlockDetailInfo(int userId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SHORT_VIEW_SELECT_SQL, Map.of("userId", userId), blockDetailsRowMapper));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    private Map<String, ? extends Comparable<? extends Comparable<?>>> createUpdateParameters(BlockDetail blockDetail) {
        return Map.of("blockedUntil", blockDetail.getBlockedUntil(),
                "reason", blockDetail.getReason(),
                "isNotify", blockDetail.isNotify(),
                "userId", blockDetail.getUserId());
    }

    private MapSqlParameterSource createInsertParameters(BlockDetail blockDetailDTO) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("userId", blockDetailDTO.getUserId());
        namedParameters.addValue("blockedUntil", Timestamp.valueOf(blockDetailDTO.getBlockedUntil()));
        namedParameters.addValue("reason", blockDetailDTO.getReason());
        namedParameters.addValue("isNotify", blockDetailDTO.isNotify());
        return namedParameters;
    }
}
