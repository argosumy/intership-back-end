package com.spd.baraholka.characteristic;

import com.spd.baraholka.characteristic.persistance.CharacteristicRepository;
import com.spd.baraholka.characteristic.persistance.CharacteristicService;
import com.spd.baraholka.characteristic.persistance.entities.Category;
import com.spd.baraholka.characteristic.persistance.entities.CategoryModel;
import com.spd.baraholka.characteristic.controller.dto.CharacteristicDTO;
import com.spd.baraholka.characteristic.service.CharacteristicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CharacteristicServiceImplTest {
    private CharacteristicService characteristicService;
    private CharacteristicRepository characteristicRepository;
    private CharacteristicDTO characteristic;
    private List<CategoryModel> categoryModelList;

    @BeforeEach
    void initializing() {
        characteristicRepository = Mockito.mock(CharacteristicRepository.class);
        characteristicService = new CharacteristicServiceImpl(characteristicRepository);
        characteristic = new CharacteristicDTO("color",
                "red",
                true,
                Category.OTHER);
        categoryModelList = List.of(new CategoryModel(Category.OTHER, Set.of("color", "weight")));
    }

    @Test
    @DisplayName("get all categories with his characteristics")
    void testRead() {
        Mockito.when(characteristicRepository.readAllCategoryWithCharacteristics()).thenReturn(categoryModelList);
        assertAll(() -> assertEquals(categoryModelList, characteristicService.readAllCategoryWithCharacteristics()));
    }
}