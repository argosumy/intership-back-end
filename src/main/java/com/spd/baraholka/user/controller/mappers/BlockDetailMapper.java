package com.spd.baraholka.user.controller.mappers;

import com.spd.baraholka.user.controller.dto.EditBlockDetailDTO;
import com.spd.baraholka.user.controller.dto.FullBlockDetailDTO;
import com.spd.baraholka.user.controller.dto.ShortViewBlockDetailDTO;
import com.spd.baraholka.user.persistance.entities.BlockDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlockDetailMapper {

    public BlockDetail convertToEntity(EditBlockDetailDTO editBlockDetailDTO) {
        BlockDetail blockDetail = new BlockDetail();
        blockDetail.setUserId(editBlockDetailDTO.getUserId());
        blockDetail.setBlockedUntil(editBlockDetailDTO.getBlockedUntil());
        blockDetail.setReason(editBlockDetailDTO.getReason());
        blockDetail.setNotify(editBlockDetailDTO.isNotify());
        return blockDetail;
    }

    public ShortViewBlockDetailDTO convertToShortViewDTO(BlockDetail blockDetail) {
        ShortViewBlockDetailDTO blockDetailDTO = new ShortViewBlockDetailDTO();
        blockDetailDTO.setUserId(blockDetail.getUserId());
        blockDetailDTO.setBlocked(blockDetail.isBlocked());
        blockDetailDTO.setBlockedUntil(blockDetail.getBlockedUntil());
        return blockDetailDTO;
    }

    public FullBlockDetailDTO convertToDTO(BlockDetail blockDetail) {
        FullBlockDetailDTO blockDetailDTO = new FullBlockDetailDTO();
        blockDetailDTO.setUserId(blockDetail.getUserId());
        blockDetailDTO.setReason(blockDetail.getReason());
        blockDetailDTO.setBlockedUntil(blockDetail.getBlockedUntil());
        blockDetailDTO.setNotify(blockDetail.isNotify());
        blockDetailDTO.setBlocked(blockDetail.isBlocked());
        return blockDetailDTO;
    }

    public List<ShortViewBlockDetailDTO> convertToDTOList(List<BlockDetail> blockDetails) {
        return blockDetails.stream().map(this::convertToShortViewDTO).collect(Collectors.toList());
    }
}
