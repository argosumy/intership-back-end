package com.spd.baraholka.wishlist.service;

import com.spd.baraholka.wishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public void save(int userId, int advertisementsId) {
        wishlistRepository.save(userId, advertisementsId);
    }

    @Override
    public List<Integer> read(int userId) {
        return wishlistRepository.read(userId);
    }

    @Override
    public void delete(int userId, int advertisementsId) {
        wishlistRepository.delete(userId, advertisementsId);
    }
}
