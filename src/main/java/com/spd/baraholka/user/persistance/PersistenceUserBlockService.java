package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.persistance.entities.BlockDetail;

import java.util.List;
import java.util.Optional;

public interface PersistenceUserBlockService {

    BlockDetail insertBlockDetails(BlockDetail blockDetail);

    Optional<Boolean> isUserAlreadyBlocked(int id);

    BlockDetail updateBlockDetails(BlockDetail blockDetail);

    Optional<BlockDetail> selectShortBlockDetailInfo(int userId);

    List<BlockDetail> selectAllUsersBlockDetails();

    BlockDetail selectBlockDetailByUserId(int userId);
}
