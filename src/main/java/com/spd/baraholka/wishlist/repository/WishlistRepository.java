package com.spd.baraholka.wishlist.repository;

import java.util.List;

public interface WishlistRepository {
    void save(int userId, int advertisementsId);

    List<Integer> read(int userId);

    void delete(int userId, int advertisementsId);
}
