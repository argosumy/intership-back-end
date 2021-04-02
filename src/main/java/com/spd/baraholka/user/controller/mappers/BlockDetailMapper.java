package com.spd.baraholka.user.controller.mappers;

import com.spd.baraholka.user.controller.dto.BlockDetailDTO;
import com.spd.baraholka.user.controller.dto.ShortViewBlockDetailDTO;
import com.spd.baraholka.user.persistance.entities.BlockDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public ShortViewBlockDetailDTO convertToDTO(BlockDetail blockDetail) {
        ShortViewBlockDetailDTO blockDetailDTO = new ShortViewBlockDetailDTO();
        blockDetailDTO.setUserId(blockDetail.getUserId());
        blockDetailDTO.setBlocked(blockDetail.isBlocked());
        blockDetailDTO.setBlockedUntil(blockDetail.getBlockedUntil());
        return blockDetailDTO;
    }

    public List<ShortViewBlockDetailDTO> convertToDTOList(List<BlockDetail> blockDetails) {
        return blockDetails.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
