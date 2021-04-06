package com.spd.baraholka.profile.statistic.controller;

import com.spd.baraholka.profile.statistic.controller.dto.FactoryDTO;
import com.spd.baraholka.profile.statistic.controller.dto.StatisticDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/statistic/categories")
public class StatisticController {
    private final FactoryDTO factoryDTO;

    public StatisticController(FactoryDTO factoryDTO) {
        this.factoryDTO = factoryDTO;
    }

    @GetMapping
    public StatisticDTO getStatistic(@RequestParam String type) {
        return factoryDTO.getDTO(type);
    }
}