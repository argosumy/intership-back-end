package com.spd.baraholka.wishlist.service;

import java.util.List;

public interface WishlistService {

    void save(int userId, int advertisementsId);

    List<Integer> read(int userId);

    void delete(int userId, int advertisementsId);
}
