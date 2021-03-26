package com.spd.baraholka.profile.statistic.controller.dto;

import java.util.List;
import java.util.Map;

public class StatisticDto {
    private final Map<String, List<Integer>> dtoStatistic;

    public StatisticDto(Map<String, List<Integer>> dtoStatistic) {
        this.dtoStatistic = dtoStatistic;
    }

    public Map<String, List<Integer>> getDtoStatistic() {
        return dtoStatistic;
    }
}
