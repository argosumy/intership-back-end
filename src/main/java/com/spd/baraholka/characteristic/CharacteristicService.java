package com.spd.baraholka.characteristic;

import java.util.List;

public interface CharacteristicService {
    int save(Characteristic characteristic);

    List<CategoryDTO> read();
}
