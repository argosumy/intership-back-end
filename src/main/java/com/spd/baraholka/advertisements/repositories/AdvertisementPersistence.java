package com.spd.baraholka.advertisements.repositories;

import com.spd.baraholka.advertisements.persistance.Advertisement;

import java.util.List;

public interface AdvertisementPersistence {

    List<Advertisement> findAdsByTitle(String title);

    List<Advertisement> findAdsByDescription(String title);
}
