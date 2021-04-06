package com.spd.baraholka.views;

import com.spd.baraholka.user.service.UserService;
import com.spd.baraholka.views.persistance.entities.View;
import com.spd.baraholka.views.persistance.repository.ViewRepository;
import com.spd.baraholka.views.service.ViewService;
import com.spd.baraholka.views.service.ViewServiceImpl;
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
    private UserService userService;

    @BeforeEach
    void initializing() {
        viewRepository = Mockito.mock(ViewRepository.class);
        viewService = new ViewServiceImpl(viewRepository, userService);
    }

    @Test
    @DisplayName("test reading list of all views for special user (userId)")
    void testRead() {
        List<View> list = new ArrayList<>();
        View view = new View(ID, USER_ID, ADVERTISEMENT_ID, DATE_TIME);
        list.add(view);

        Mockito.when(viewRepository.read(USER_ID)).thenReturn(list);
        assertAll(() -> assertEquals(list, viewService.read()));
    }
}