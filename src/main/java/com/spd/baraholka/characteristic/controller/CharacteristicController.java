package com.spd.baraholka.characteristic.controller;

import com.spd.baraholka.characteristic.persistance.CharacteristicService;
import com.spd.baraholka.characteristic.persistance.entities.CategoryModel;
import com.spd.baraholka.characteristic.persistance.entities.Characteristic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories-characteristics")
public class CharacteristicController {
    private final CharacteristicService characteristicService;

    @Autowired
    public CharacteristicController(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @GetMapping
    public List<CategoryModel> getAllCategoryWithCharacteristics() {
        return characteristicService.readAllCategoryWithCharacteristics();
    }

    @GetMapping("/not-approved-characteristics")
    public List<Characteristic> getAllNotApprovedCharacteristics() {
        return characteristicService.readNotApproved();
    }

    @PutMapping("/{id}")
    public void updateCharacteristicStatus(@PathVariable int id) {
        characteristicService.updateApproveStatus(id);
    }
}
