package com.spd.baraholka.user.persistance.mappers;

import com.spd.baraholka.user.persistance.entities.BlockDetail;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class BlockDetailRowMapper implements RowMapper<BlockDetail> {

    @Override
    public BlockDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        BlockDetail blockDetail = new BlockDetail();
        blockDetail.setUserId(rs.getInt("user_id"));
        blockDetail.setReason(rs.getString("reason"));
        blockDetail.setBlocked(rs.getBoolean("is_blocked"));
        blockDetail.setNotify(rs.getBoolean("is_notify"));
        setBannedUntil(rs, blockDetail);
        return blockDetail;
    }

    private void setBannedUntil(ResultSet resultSet, BlockDetail blockDetail) throws SQLException {
        Timestamp blockedUntilTimeStamp = resultSet.getTimestamp("blocked_until");
        LocalDateTime blockedUntilDate = blockedUntilTimeStamp.toLocalDateTime();
        blockDetail.setBlockedUntil(blockedUntilDate);
    }
}
