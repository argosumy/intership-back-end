package com.spd.baraholka.characteristic;

import java.util.List;

public interface CharacteristicRepository {

    int save(Characteristic characteristic);

    List<CategoryDTO> read();
}
