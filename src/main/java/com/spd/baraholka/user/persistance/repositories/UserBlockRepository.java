package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.controller.dto.BlockDetailDTO;
import com.spd.baraholka.user.persistance.PersistenceUserBlockService;
import org.springframework.stereotype.Repository;

@Repository
public class UserBlockRepository implements PersistenceUserBlockService {

    @Override
    public int blockUser(BlockDetailDTO blockDetailDTO) {
        return 0;
    }
}
