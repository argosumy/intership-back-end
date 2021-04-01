package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.controller.dto.BanDetailDTO;

public interface PersistenceUserBlockService {

    int blockUser(BanDetailDTO banDetailDTO);
}
