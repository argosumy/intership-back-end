package com.spd.baraholka.advertisement.service;

import com.spd.baraholka.advertisement.persistance.Advertisement;
import com.spd.baraholka.advertisement.persistance.AdvertisementStatus;

public interface PersistenceAdvertisementService {

    int saveAdvertisement(Advertisement advertisement);

    int updateAdvertisement(Advertisement advertisement);

    int updateAdvertisementStatus(int id, AdvertisementStatus status);
}
