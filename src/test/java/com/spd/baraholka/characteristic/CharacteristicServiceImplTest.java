package com.spd.baraholka.characteristic;

import com.spd.baraholka.characteristic.persistance.dto.CategoryDTO;
import com.spd.baraholka.characteristic.persistance.entities.Characteristic;
import com.spd.baraholka.characteristic.persistance.CharacteristicRepository;
import com.spd.baraholka.characteristic.persistance.CharacteristicService;
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
    private Characteristic characteristic;
    private List<CategoryDTO> categoryDTOList;

    @BeforeEach
    void initializing() {
        characteristicRepository = Mockito.mock(CharacteristicRepository.class);
        characteristicService = new CharacteristicServiceImpl(characteristicRepository);
        characteristic = new Characteristic(1,
                "OTHER",
                "color",
                "red",
                true);
        categoryDTOList = List.of(new CategoryDTO("OTHER", Set.of("color", "weight")));
    }

    @Test
    @DisplayName("save Characteristic return id")
    void testSave() {
        Mockito.when(characteristicRepository.save(characteristic)).thenReturn(1);
        assertAll(() -> assertEquals(1, characteristicService.save(characteristic)));
    }

    @Test
    @DisplayName("get all categories with his characteristics")
    void testRead() {
        Mockito.when(characteristicRepository.read()).thenReturn(categoryDTOList);
        assertAll(() -> assertEquals(categoryDTOList, characteristicService.read()));
    }
}