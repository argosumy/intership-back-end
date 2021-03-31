package com.spd.baraholka.profile.statistic.controller.dto;

import java.util.Map;

public class FullStatisticByCategoryDto {
    private final Map<String, Integer> dtoStatisticFull;

    public FullStatisticByCategoryDto(Map<String, Integer> dtoStatistic) {
        this.dtoStatisticFull = dtoStatistic;
    }

    public Map<String, Integer> getDtoStatisticAllCategory() {
        return dtoStatisticFull;
    }
}
