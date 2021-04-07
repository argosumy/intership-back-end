package com.spd.baraholka.advertisement.persistance;

import com.spd.baraholka.advertisement.controller.dto.FilterDTO;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.characteristic.persistance.entities.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersistenceAdvertisementService {

    void updatePromotionDate(int idAdvertisement);

    int updateAdvertisement(Advertisement advertisement);

    int updateAdvertisementStatus(int id, AdvertisementStatus status);

    List<Advertisement> getAllActive();

    List<Advertisement> getPublishedFilteredAds(FilterDTO filterDTO);

    List<Advertisement> getAllPublishedByCategory(Category category);

    List<Advertisement> getAllPublishedByPrice(double min, double max);

    List<Advertisement> getAllPublishedByCategoryAndPrice(Category category, double min, double max);

    List<Advertisement> getWishListByUser(int userId);

    List<Advertisement> getAllAdsWithStatusByUser(int userId, AdvertisementStatus status);

    List<Advertisement> getAllAdsByUser(int userId);

    Optional<Advertisement> findDraftAdById(int id);

    int insertAdvertisement(Advertisement advertisement);

    Optional<Boolean> isExist(int intValue);

    void changeStatusArchivedOnDeleted();

    Optional<Advertisement> selectAdvertisementById(int id);
}
