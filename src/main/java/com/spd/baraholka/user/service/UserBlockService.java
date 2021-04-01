package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.dto.BanDetailDTO;
import com.spd.baraholka.user.persistance.PersistenceUserBlockService;
import org.springframework.stereotype.Service;

@Service
public class UserBlockService {

    private final PersistenceUserBlockService persistenceUserBlockService;

    public UserBlockService(PersistenceUserBlockService persistenceUserBlockService) {
        this.persistenceUserBlockService = persistenceUserBlockService;
    }

    public int changeUserBlockedStatus(BanDetailDTO banDetailDTO) {
        return persistenceUserBlockService.blockUser(banDetailDTO);
    }
}
