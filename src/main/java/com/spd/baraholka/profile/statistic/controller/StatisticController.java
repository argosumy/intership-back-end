package com.spd.baraholka.profile.statistic.controller;

import com.spd.baraholka.profile.statistic.controller.dto.CountCategoryForPeriodDto;
import com.spd.baraholka.profile.statistic.controller.dto.FullStatisticByCategoryDto;
import com.spd.baraholka.profile.statistic.persistance.repository.SQLQueries;
import com.spd.baraholka.profile.statistic.service.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class StatisticController {
    private final StatisticService service;

    public StatisticController(StatisticService service) {
        this.service = service;
    }

    @GetMapping(value = "/statistic/category/full")
    public FullStatisticByCategoryDto getFullStatisticByCategory() {
        FullStatisticByCategoryDto responseDto = new FullStatisticByCategoryDto(service.getCountByGroupCategoryFull(SQLQueries.GET_COUNT_CATEGORY_VIEW_FULL));
        return responseDto;
    }

    @GetMapping(value = "/statistic/category/new")
    public CountCategoryForPeriodDto getCountCategoryForPeriodNewAd() {
        CountCategoryForPeriodDto responseDto = new CountCategoryForPeriodDto(
                service.getCountNewAdvertisementByCategoryForPeriod(SQLQueries.GET_COUNT_CATEGORY_BY_GROUP_FOR_PERIOD_NEW));
        return responseDto;
    }

    @GetMapping(value = "/statistic/category/view")
    public CountCategoryForPeriodDto getCountCategoryViewForPeriod() {
        CountCategoryForPeriodDto responseDto = new CountCategoryForPeriodDto(
                service.getCountNewAdvertisementByCategoryForPeriod(SQLQueries.GET_COUNT_CATEGORY_VIEW_FOR_PERIOD));
        return responseDto;
    }

    @PostConstruct
    public void construct() {
        /*TEST*/
        service.getCountNewAdvertisementByCategoryForPeriod(SQLQueries.GET_COUNT_CATEGORY_VIEW_FOR_PERIOD);
    }
}
