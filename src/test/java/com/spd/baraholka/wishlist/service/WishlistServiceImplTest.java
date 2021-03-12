package com.spd.baraholka.wishlist.service;

import com.spd.baraholka.wishlist.repository.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WishlistServiceImplTest {
    WishlistService wishlistService;
    WishlistRepository wishlistRepository;

    @BeforeEach
    void initializing() {
        wishlistRepository = Mockito.mock(WishlistRepository.class);
        wishlistService = new WishlistServiceImpl(wishlistRepository);
    }

    @Test
    void read() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Mockito.when(wishlistRepository.read(1)).thenReturn(list);

        assertAll(() -> assertEquals(list, wishlistService.read(1)));
    }
}