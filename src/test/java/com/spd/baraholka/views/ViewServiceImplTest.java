package com.spd.baraholka.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewServiceImplTest {
    private static final int ID = 1;
    private static final int USER_ID = 15;
    private static final int ADVERTISEMENT_ID = 2;
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2021,  1, 1, 18, 10, 15);
    private ViewService viewService;
    private ViewRepository viewRepository;

    @BeforeEach
    void initializing() {
        viewRepository = Mockito.mock(ViewRepository.class);
        viewService = new ViewServiceImpl(viewRepository);
    }

    @Test
    @DisplayName("test reading list of all views for special user (userId)")
    void testRead() {
        List<View> list = new ArrayList<>();
        View view = new View(ID, USER_ID, ADVERTISEMENT_ID, DATE_TIME);
        list.add(view);

        Mockito.when(viewRepository.read(USER_ID)).thenReturn(list);
        assertAll(() -> assertEquals(list, viewService.read(USER_ID)));
    }

    @Test
    @DisplayName("test saving view advertisement (advertisementsId) for user (userId)")
    void testSave() {
        Mockito.when(viewRepository.save(USER_ID, ADVERTISEMENT_ID)).thenReturn(USER_ID);
        assertAll(() -> assertEquals(USER_ID, viewService.save(USER_ID, ADVERTISEMENT_ID)));
    }
}