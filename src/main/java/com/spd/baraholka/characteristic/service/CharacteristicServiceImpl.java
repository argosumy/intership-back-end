package com.spd.baraholka.characteristic.service;

import com.spd.baraholka.characteristic.persistance.entities.CategoryModel;
import com.spd.baraholka.characteristic.persistance.entities.Characteristic;
import com.spd.baraholka.characteristic.persistance.CharacteristicRepository;
import com.spd.baraholka.characteristic.persistance.CharacteristicService;
import com.spd.baraholka.characteristic.controller.dto.CharacteristicDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {
    private final CharacteristicRepository characteristicRepository;

    @Autowired
    public CharacteristicServiceImpl(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    @Override
    public void save(int adId, CharacteristicDTO characteristic) {
        characteristicRepository.save(adId, characteristic);
    }

    @Override
    public void update(int id, List<CharacteristicDTO> characteristics) {
        characteristicRepository.delete(id);
        characteristics.forEach(characteristicDTO -> characteristicRepository.save(id, characteristicDTO));
    }

    @Override
    public void updateApproveStatus(int id) {
        characteristicRepository.updateApproveStatus(id);
    }

    @Override
    public List<CategoryModel> readAllCategoryWithCharacteristics() {
        return characteristicRepository.readAllCategoryWithCharacteristics();
    }

    @Override
    public void delete(int id) {
        characteristicRepository.delete(id);
    }

    @Override
    public List<Characteristic> readForAdId(int adId) {
        return characteristicRepository.readForAdId(adId);
    }

    @Override
    public List<Characteristic> readNotApproved() {
        return characteristicRepository.readNotApproved();
    }
}
