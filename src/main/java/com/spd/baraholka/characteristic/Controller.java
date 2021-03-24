package com.spd.baraholka.characteristic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    private final CharacteristicService characteristicService;

    @Autowired
    public Controller(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @GetMapping("/")
    public List<CategoryDTO> getAllViews() {
        return characteristicService.read();
    }
}
