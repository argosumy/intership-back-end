package com.spd.baraholka.profile.statistic.controller;

import com.spd.baraholka.profile.statistic.controller.dto.CountCategoryForPeriodDto;
import com.spd.baraholka.profile.statistic.controller.dto.FullStatisticByCategoryDto;
import com.spd.baraholka.profile.statistic.persistance.repository.SQLQueries;
import com.spd.baraholka.profile.statistic.service.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/statistic/category")
public class StatisticController {
    private final StatisticService service;

    public StatisticController(StatisticService service) {
        this.service = service;
    }

    @GetMapping(value = "/full")
    public FullStatisticByCategoryDto getFullStatisticByCategory() {
        FullStatisticByCategoryDto responseDto = new FullStatisticByCategoryDto(service.getCountCategoryByGroupFull(SQLQueries.GET_COUNT_CATEGORY_VIEW_FULL));
        return responseDto;
    }

    @GetMapping(value = "/new")
    public CountCategoryForPeriodDto getCountCategoryForPeriodNewAd() {
        CountCategoryForPeriodDto responseDto = new CountCategoryForPeriodDto(
                service.getCountCategoryByGroupForPeriod(SQLQueries.GET_COUNT_CATEGORY_BY_GROUP_FOR_PERIOD_NEW));
        return responseDto;
    }

    @GetMapping(value = "/view")
    public CountCategoryForPeriodDto getCountCategoryViewForPeriod() {
        CountCategoryForPeriodDto responseDto = new CountCategoryForPeriodDto(
                service.getCountCategoryByGroupForPeriod(SQLQueries.GET_COUNT_CATEGORY_VIEW_FOR_PERIOD));
        return responseDto;
    }
}
