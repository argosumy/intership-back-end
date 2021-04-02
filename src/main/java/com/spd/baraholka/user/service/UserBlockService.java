package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.dto.BlockDetailDTO;
import com.spd.baraholka.user.controller.dto.ShortViewBlockDetailDTO;
import com.spd.baraholka.user.controller.mappers.BlockDetailMapper;
import com.spd.baraholka.user.persistance.PersistenceUserBlockService;
import com.spd.baraholka.user.persistance.entities.BlockDetail;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBlockService {

    private final PersistenceUserBlockService persistenceUserBlockService;
    private final BlockDetailMapper blockDetailMapper;

    public UserBlockService(PersistenceUserBlockService persistenceUserBlockService, BlockDetailMapper blockDetailMapper) {
        this.persistenceUserBlockService = persistenceUserBlockService;
        this.blockDetailMapper = blockDetailMapper;
    }

    public int blockUser(BlockDetailDTO blockDetailDTO) {
        int id = blockDetailDTO.getUserId();
        BlockDetail blockDetail = blockDetailMapper.convertToEntity(blockDetailDTO);
        boolean alreadyBlocked = persistenceUserBlockService.isUserAlreadyBlocked(id).orElse(false);
        if (alreadyBlocked) {
            return persistenceUserBlockService.updateBlockDetails(blockDetail);
        } else {
            return persistenceUserBlockService.insertBlockDetails(blockDetail);
        }
    }

    public ShortViewBlockDetailDTO getShortViewUserBlockDetail(int userId) {
        Optional<BlockDetail> blockDetail = persistenceUserBlockService.selectShortBlockDetailInfo(userId);
        if (blockDetail.isPresent()) {
            return blockDetailMapper.convertToDTO(blockDetail.get());
        } else {
            return createDefaultDTO();
        }
    }

    private ShortViewBlockDetailDTO createDefaultDTO() {
        ShortViewBlockDetailDTO blockDetailDTO = new ShortViewBlockDetailDTO();
        blockDetailDTO.setBlocked(false);
        blockDetailDTO.setBlockedUntil(null);
        return blockDetailDTO;
    }
}
