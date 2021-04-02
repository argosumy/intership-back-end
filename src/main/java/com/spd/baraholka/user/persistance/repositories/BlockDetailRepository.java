package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserBlockService;
import com.spd.baraholka.user.persistance.entities.BlockDetail;
import com.spd.baraholka.user.persistance.mappers.BlockDetailRowMapper;
import com.spd.baraholka.user.persistance.mappers.ShortViewBlockDetailsRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BlockDetailRepository implements PersistenceUserBlockService {

    private static final String INSERT_SQL =
            "INSERT INTO users_block_details (user_id, blocked_until, reason, is_notify) VALUES (:userId, :blockedUntil, :reason, :isNotify) RETURNING id";
    private static final String IS_EXIST_SQL = "SELECT count(*) <> 0 FROM users_block_details WHERE user_id=:id";
    private static final String UPDATE_SQL = "UPDATE users_block_details SET blocked_until=:blockedUntil, reason=:reason, is_notify=:isNotify WHERE user_id=:userId";
    private static final String SHORT_VIEW_SELECT_SQL = "SELECT is_blocked, blocked_until, user_id FROM users_block_details WHERE user_id=:userId";
    private static final String ALL_USERS_BLOCK_DETAILS_SELECT_SQL = "SELECT is_blocked, blocked_until, user_id FROM users_block_details";
    private static final String SELECT_BLOCK_DETAIL_BY_ROW_ID = "SELECT * FROM users_block_details WHERE user_id=:userId";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ShortViewBlockDetailsRowMapper shortViewBlockDetailsRowMapper;
    private final BlockDetailRowMapper blockDetailRowMapper;

    public BlockDetailRepository(NamedParameterJdbcTemplate jdbcTemplate,
                                 ShortViewBlockDetailsRowMapper shortViewBlockDetailsRowMapper,
                                 BlockDetailRowMapper blockDetailRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.shortViewBlockDetailsRowMapper = shortViewBlockDetailsRowMapper;
        this.blockDetailRowMapper = blockDetailRowMapper;
    }

    @Override
    public BlockDetail insertBlockDetails(BlockDetail blockDetail) {
        MapSqlParameterSource insertParameters = createInsertParameters(blockDetail);
        jdbcTemplate.update(INSERT_SQL, insertParameters);
        return selectBlockDetailByUserId(blockDetail.getUserId());
    }

    @Override
    public Optional<Boolean> isUserAlreadyBlocked(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(IS_EXIST_SQL, Map.of("id", id), Boolean.class));
    }

    @Override
    public BlockDetail updateBlockDetails(BlockDetail blockDetail) {
        Map<String, ? extends Comparable<? extends Comparable<?>>> updateParameters = createUpdateParameters(blockDetail);
        jdbcTemplate.update(UPDATE_SQL, updateParameters);
        return selectBlockDetailByUserId(blockDetail.getUserId());
    }

    @Override
    public Optional<BlockDetail> selectShortBlockDetailInfo(int userId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SHORT_VIEW_SELECT_SQL, Map.of("userId", userId), shortViewBlockDetailsRowMapper));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public BlockDetail selectBlockDetailByUserId(int userId) {
        return jdbcTemplate.queryForObject(SELECT_BLOCK_DETAIL_BY_ROW_ID, Map.of("userId", userId), blockDetailRowMapper);
    }

    public List<BlockDetail> selectAllUsersBlockDetails() {
        return jdbcTemplate.query(ALL_USERS_BLOCK_DETAILS_SELECT_SQL, shortViewBlockDetailsRowMapper);
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
