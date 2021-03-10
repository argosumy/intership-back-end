package com.spd.baraholka.advertisements.services;

import com.spd.baraholka.advertisements.persistance.Advertisement;

public interface PersistenceAdvertisementService {

    int saveAdvertisement(Advertisement advertisement);

    int updateAdvertisement(Advertisement advertisement);
}
