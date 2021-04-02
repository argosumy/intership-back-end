package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.dto.BlockDetailDTO;
import com.spd.baraholka.user.controller.dto.FullBlockDetailDTO;
import com.spd.baraholka.user.controller.dto.ShortViewBlockDetailDTO;
import com.spd.baraholka.user.controller.mappers.BlockDetailMapper;
import com.spd.baraholka.user.persistance.PersistenceUserBlockService;
import com.spd.baraholka.user.persistance.entities.BlockDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBlockService {

    private final PersistenceUserBlockService persistenceUserBlockService;
    private final BlockDetailMapper blockDetailMapper;

    public UserBlockService(PersistenceUserBlockService persistenceUserBlockService, BlockDetailMapper blockDetailMapper) {
        this.persistenceUserBlockService = persistenceUserBlockService;
        this.blockDetailMapper = blockDetailMapper;
    }

    public FullBlockDetailDTO blockUser(BlockDetailDTO blockDetailDTO) {
        int id = blockDetailDTO.getUserId();
        BlockDetail newBlockDetail = blockDetailMapper.convertToEntity(blockDetailDTO);
        boolean alreadyBlocked = persistenceUserBlockService.isUserAlreadyBlocked(id).orElse(false);
        BlockDetail blockDetail;
        if (alreadyBlocked) {
            blockDetail = persistenceUserBlockService.updateBlockDetails(newBlockDetail);
        } else {
            blockDetail = persistenceUserBlockService.insertBlockDetails(newBlockDetail);
        }
        return blockDetailMapper.convertToDTO(blockDetail);
    }

    public ShortViewBlockDetailDTO getShortViewUserBlockDetail(int userId) {
        Optional<BlockDetail> blockDetail = persistenceUserBlockService.selectShortBlockDetailInfo(userId);
        if (blockDetail.isPresent()) {
            return blockDetailMapper.convertToShortViewDTO(blockDetail.get());
        } else {
            return createDefaultDTO();
        }
    }

    public List<ShortViewBlockDetailDTO> getAllUsersBlockDetails() {
        List<BlockDetail> blockDetails = persistenceUserBlockService.selectAllUsersBlockDetails();
        return blockDetailMapper.convertToDTOList(blockDetails);
    }

    public ShortViewBlockDetailDTO unBlockUser(int userId, boolean isNotify) {
        BlockDetail unblockDetail = createUnblockDetail(userId, isNotify);
        BlockDetail blockDetail = persistenceUserBlockService.updateBlockDetails(unblockDetail);
        return blockDetailMapper.convertToShortViewDTO(blockDetail);
    }

    private BlockDetail createUnblockDetail(int userId, boolean isNotify) {
        BlockDetail blockDetail = new BlockDetail();
        blockDetail.setUserId(userId);
        blockDetail.setReason(null);
        blockDetail.setBlockedUntil(null);
        blockDetail.setBlocked(false);
        blockDetail.setNotify(isNotify);
        return blockDetail;
    }

    private ShortViewBlockDetailDTO createDefaultDTO() {
        ShortViewBlockDetailDTO blockDetailDTO = new ShortViewBlockDetailDTO();
        blockDetailDTO.setBlocked(false);
        blockDetailDTO.setBlockedUntil(null);
        return blockDetailDTO;
    }
}
