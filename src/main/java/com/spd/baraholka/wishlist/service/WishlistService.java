package com.spd.baraholka.wishlist.service;

import java.util.List;

public interface WishlistService {

    void save(int advertisementsId);

    List<Integer> read();

    void delete(int advertisementsId);
}
