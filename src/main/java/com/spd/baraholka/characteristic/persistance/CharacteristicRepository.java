package com.spd.baraholka.characteristic.persistance;

import com.spd.baraholka.characteristic.persistance.entities.CategoryModel;
import com.spd.baraholka.characteristic.persistance.entities.Characteristic;
import com.spd.baraholka.characteristic.persistance.entities.CharacteristicDTO;

import java.util.List;

public interface CharacteristicRepository {

    void save(int adId, CharacteristicDTO characteristic);

    void updateApproveStatus(int id);

    void delete(int id);

    List<CategoryModel> readAllCategoryWithCharacteristics();

    List<Characteristic> readForAdId(int adId);

    List<Characteristic> readNotApproved();
}
