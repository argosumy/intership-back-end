package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.persistance.entities.BlockDetail;

public interface PersistenceUserBlockService {

    int insertBlockDetails(BlockDetail blockDetailDTO);
}
