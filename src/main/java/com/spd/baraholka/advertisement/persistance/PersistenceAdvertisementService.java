package com.spd.baraholka.advertisement.persistance;

import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;

import java.util.Optional;

public interface PersistenceAdvertisementService {

    int insertAdvertisement(Advertisement advertisement);

    int updateAdvertisement(Advertisement advertisement);

    int updateAdvertisementStatus(int id, AdvertisementStatus status);

    Optional<Boolean> isExist(int intValue);

    Optional<Advertisement> selectAdvertisementById(int id);
}
