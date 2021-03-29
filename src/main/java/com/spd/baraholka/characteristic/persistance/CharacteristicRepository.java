package com.spd.baraholka.characteristic.persistance;

import com.spd.baraholka.characteristic.persistance.entities.CategoryDTO;
import com.spd.baraholka.characteristic.persistance.entities.Characteristic;

import java.util.List;

public interface CharacteristicRepository {

    int save(Characteristic characteristic);

    List<CategoryDTO> read();
}
