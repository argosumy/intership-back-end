package com.spd.baraholka.user.controller.mappers;

import com.spd.baraholka.user.controller.dto.BlockDetailDTO;
import com.spd.baraholka.user.persistance.entities.BlockDetail;
import org.springframework.stereotype.Component;

@Component
public class BlockDetailMapper {

    public BlockDetail convertToEntity(BlockDetailDTO blockDetailDTO) {
        BlockDetail blockDetail = new BlockDetail();
        blockDetail.setUserId(blockDetailDTO.getUserId());
        blockDetail.setBlockedUntil(blockDetailDTO.getBanedUntil());
        blockDetail.setReason(blockDetailDTO.getReason());
        blockDetail.setNotify(blockDetailDTO.isNotify());
        return blockDetail;
    }
}
