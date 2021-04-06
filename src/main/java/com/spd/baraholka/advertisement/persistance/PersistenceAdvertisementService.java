package com.spd.baraholka.advertisement.persistance;

import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersistenceAdvertisementService {

    void updatePromotionDate(int idAdvertisement);

    int updateAdvertisement(Advertisement advertisement);

    int updateAdvertisementStatus(int id, AdvertisementStatus status);

    List<Advertisement> getAllActive();

    Optional<Advertisement> findDraftAdById(int id);

    int insertAdvertisement(Advertisement advertisement);

    Optional<Boolean> isExist(int intValue);

    Optional<Advertisement> selectAdvertisementById(int id);
}
