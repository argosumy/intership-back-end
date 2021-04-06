package com.spd.baraholka.wishlist.controller;

import com.spd.baraholka.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/{advertisementsId}")
    public void saveWishlist(@PathVariable int userId, @PathVariable int advertisementsId) {
        wishlistService.save(userId, advertisementsId);
    }

    @DeleteMapping("/{advertisementsId}")
    public void deleteWishlist(@PathVariable int userId, @PathVariable int advertisementsId) {
        wishlistService.delete(userId, advertisementsId);
    }

    @GetMapping
    public List<Integer> getWishlist(@PathVariable int userId) {
        return wishlistService.read(userId);
    }
}
