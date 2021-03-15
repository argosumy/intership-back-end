package com.spd.baraholka.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class ViewServiceImplTest {
    ViewService viewService;
    ViewRepository viewRepository;

    @BeforeEach
    void initializing() {
        viewRepository = Mockito.mock(ViewRepository.class);
        viewService = new ViewServiceImpl(viewRepository);
    }

    @Test
    void read() {
        List<View> list = new ArrayList<>();
        View view = new View();
        view.setUserId(15);
        view.setAdvertisementId(2);
        list.add(view);

        Mockito.when(viewRepository.read(15)).thenReturn(list);

        assertAll(() -> assertEquals(list, viewService.read(15)));
    }
}