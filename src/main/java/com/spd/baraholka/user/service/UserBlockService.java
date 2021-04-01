package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.dto.BlockDetailDTO;
import com.spd.baraholka.user.persistance.PersistenceUserBlockService;
import org.springframework.stereotype.Service;

@Service
public class UserBlockService {

    private final PersistenceUserBlockService persistenceUserBlockService;

    public UserBlockService(PersistenceUserBlockService persistenceUserBlockService) {
        this.persistenceUserBlockService = persistenceUserBlockService;
    }

    public int blockUser(BlockDetailDTO blockDetailDTO) {
        return persistenceUserBlockService.blockUser(blockDetailDTO);
    }
}
