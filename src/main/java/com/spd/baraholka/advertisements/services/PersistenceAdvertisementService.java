package com.spd.baraholka.advertisements.services;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.advertisements.persistance.AdvertisementStatus;

import java.util.List;

public interface PersistenceAdvertisementService {

    int saveAdvertisement(Advertisement advertisement);

    int updateAdvertisement(Advertisement advertisement);

    int updateAdvertisementStatus(int id, AdvertisementStatus status);

    List<Advertisement> findAdsByTitle(String title);

    List<Advertisement> findAdsByDescription(String description);
}
