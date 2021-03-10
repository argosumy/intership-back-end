package com.spd.baraholka.advertisements.repositories;

import com.spd.baraholka.advertisements.entities.Advertisement;
import com.spd.baraholka.comments.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface AdvertisementPersistence {

    List<Advertisement> findAdsByTitle(String title);

    List<Advertisement> findAdsByDescription(String title);
}
