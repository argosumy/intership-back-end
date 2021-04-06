package com.spd.baraholka.characteristic.persistance;

import com.spd.baraholka.characteristic.persistance.entities.CategoryModel;
import com.spd.baraholka.characteristic.persistance.entities.Characteristic;
import com.spd.baraholka.characteristic.controller.dto.CharacteristicDTO;

import java.util.List;

public interface CharacteristicService {
    void save(int adId, CharacteristicDTO characteristic);

    void update(int id, List<CharacteristicDTO> characteristics);

    void updateApproveStatus(int id);

    void delete(int id);

    List<CategoryModel> readAllCategoryWithCharacteristics();

    List<CharacteristicDTO> readForAdId(int adId);

    List<Characteristic> readNotApproved();
}
