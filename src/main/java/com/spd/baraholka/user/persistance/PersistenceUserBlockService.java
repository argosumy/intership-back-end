package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.controller.dto.BlockDetailDTO;

public interface PersistenceUserBlockService {

    int blockUser(BlockDetailDTO blockDetailDTO);
}
