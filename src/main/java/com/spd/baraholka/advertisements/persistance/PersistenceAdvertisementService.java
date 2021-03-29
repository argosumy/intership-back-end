package com.spd.baraholka.advertisements.persistance;

import com.spd.baraholka.advertisements.persistance.entities.Advertisement;
import com.spd.baraholka.advertisements.persistance.entities.AdvertisementStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersistenceAdvertisementService {

    int updateAdvertisement(Advertisement advertisement);

    int updateAdvertisementStatus(int id, AdvertisementStatus status);

    List<Advertisement> getAllActive();

    Optional<Advertisement> findDraftAdById(int id);

    int insertAdvertisement(Advertisement advertisement);

    Optional<Boolean> isExist(int intValue);

}
