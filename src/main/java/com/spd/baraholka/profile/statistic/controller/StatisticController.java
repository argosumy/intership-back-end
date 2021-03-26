package com.spd.baraholka.profile.statistic.controller;

import com.spd.baraholka.profile.statistic.controller.dto.StatisticDto;
import com.spd.baraholka.profile.statistic.service.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StatisticController {
    private final StatisticService service;

    public StatisticController(StatisticService service) {
        this.service = service;
    }

    @GetMapping(value = "/profile/statistic/category")
    public StatisticDto getStatisticByCategoryAll() {
        Map<String, List<Integer>> mapCountCategory = service.getCountByGroupCategory();
        StatisticDto  responseDto = new StatisticDto(mapCountCategory);
        return responseDto;
    }
}
