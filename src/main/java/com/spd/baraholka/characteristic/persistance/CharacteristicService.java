package com.spd.baraholka.characteristic.persistance;

import com.spd.baraholka.characteristic.persistance.dto.CategoryDTO;
import com.spd.baraholka.characteristic.persistance.entities.Characteristic;

import java.util.List;

public interface CharacteristicService {
    int save(Characteristic characteristic);

    List<CategoryDTO> read();
}
