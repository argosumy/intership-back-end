package com.spd.baraholka.wishlist.service;

import com.spd.baraholka.user.service.UserService;
import com.spd.baraholka.wishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserService userService;

    @Autowired
    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserService userService) {
        this.wishlistRepository = wishlistRepository;
        this.userService = userService;
    }

    @Override
    public void save(int advertisementsId) {
        int userId = userService.getCurrentUser().getId();
        wishlistRepository.save(userId, advertisementsId);
    }

    @Override
    public List<Integer> read() {
        int userId = userService.getCurrentUser().getId();

        return wishlistRepository.read(userId);
    }

    @Override
    public void delete(int advertisementsId) {
        int userId = userService.getCurrentUser().getId();
        wishlistRepository.delete(userId, advertisementsId);
    }
}
