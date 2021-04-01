package com.spd.baraholka.profile.statistic.controller.dto;

import java.util.List;
import java.util.Map;

public class CountCategoryForPeriodDto {
    private final Map<String, Map<String, List<Integer>>> dtoCountCategory;

    public CountCategoryForPeriodDto(Map<String, Map<String, List<Integer>>> dtoCountCategory) {
        this.dtoCountCategory = dtoCountCategory;
    }

    public Map<String, Map<String, List<Integer>>> getDtoCountCategory() {
        return dtoCountCategory;
    }
}
