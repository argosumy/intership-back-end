package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.persistance.entities.BlockDetail;

import java.util.Optional;

public interface PersistenceUserBlockService {

    int insertBlockDetails(BlockDetail blockDetailDTO);

    Optional<Boolean> isUserAlreadyBlocked(int id);
}
