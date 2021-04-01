package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.dto.BlockDetailDTO;
import com.spd.baraholka.user.controller.mappers.BlockDetailMapper;
import com.spd.baraholka.user.persistance.PersistenceUserBlockService;
import com.spd.baraholka.user.persistance.entities.BlockDetail;
import org.springframework.stereotype.Service;

@Service
public class UserBlockService {

    private final PersistenceUserBlockService persistenceUserBlockService;
    private final BlockDetailMapper blockDetailMapper;

    public UserBlockService(PersistenceUserBlockService persistenceUserBlockService, BlockDetailMapper blockDetailMapper) {
        this.persistenceUserBlockService = persistenceUserBlockService;
        this.blockDetailMapper = blockDetailMapper;
    }

    public int blockUser(BlockDetailDTO blockDetailDTO) {
        BlockDetail blockDetail = blockDetailMapper.convertToEntity(blockDetailDTO);
        return persistenceUserBlockService.blockUser(blockDetail);
    }
}
