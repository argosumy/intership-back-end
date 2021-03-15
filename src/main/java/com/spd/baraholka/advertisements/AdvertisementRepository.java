package com.spd.baraholka.advertisements;

import java.util.List;

public interface AdvertisementRepository {

    List<Integer> getArchivedAdvertisementForDeleting();

    void updateArchivedStatusToDeleted(int id);
}
