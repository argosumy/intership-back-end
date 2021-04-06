package com.spd.baraholka.profile.statistic.controller.dto;

import com.spd.baraholka.profile.statistic.enumes.TypeStatistic;
import com.spd.baraholka.profile.statistic.persistance.repository.SQLQueries;
import com.spd.baraholka.profile.statistic.service.StatisticService;
import org.springframework.stereotype.Component;

@Component
public class FullStatisticByCategoryDto implements StatisticDTO {
    private final StatisticService statisticService;

    public FullStatisticByCategoryDto(StatisticService statisticService) {
        this.statisticService = statisticService;
            }

    @Override
    public Object getStatistic() {
        return statisticService.getCountCategoryByGroupFull(SQLQueries.GET_COUNT_CATEGORY_VIEW_FULL);
    }

    @Override
    public String getTypeDTO() {
        return TypeStatistic.FULL.name();
    }
}