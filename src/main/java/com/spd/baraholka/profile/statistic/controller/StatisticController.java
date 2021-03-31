package com.spd.baraholka.profile.statistic.controller;

import com.spd.baraholka.profile.statistic.controller.dto.CountNewAdvertisementByCategoryForPeriod;
import com.spd.baraholka.profile.statistic.controller.dto.FullStatisticByCategoryDto;
import com.spd.baraholka.profile.statistic.service.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {
    private final StatisticService service;

    public StatisticController(StatisticService service) {
        this.service = service;
    }

    @GetMapping(value = "/profile/statistic/category/full")
    public FullStatisticByCategoryDto getFullStatisticByCategory() {
        FullStatisticByCategoryDto responseDto = new FullStatisticByCategoryDto(service.getCountByGroupCategory());
        return responseDto;
    }

    @GetMapping(value = "/profile/statistic/category/new")
    public CountNewAdvertisementByCategoryForPeriod get() {
        CountNewAdvertisementByCategoryForPeriod responseDto = new CountNewAdvertisementByCategoryForPeriod(
                service.getCountNewAdvertisementByCategoryForPeriod());
        return responseDto;
    }
}
