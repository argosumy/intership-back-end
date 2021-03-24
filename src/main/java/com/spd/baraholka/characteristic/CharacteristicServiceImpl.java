package com.spd.baraholka.characteristic;

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
    public int save(Characteristic characteristic) {
        return characteristicRepository.save(characteristic);
    }

    @Override
    public List<CategoryDTO> read() {
        return characteristicRepository.read();
    }
}
