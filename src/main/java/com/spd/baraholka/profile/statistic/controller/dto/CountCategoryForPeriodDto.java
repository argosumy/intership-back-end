package com.spd.baraholka.profile.statistic.controller.dto;

import java.util.List;
import java.util.Map;

public class CountCategoryForPeriodDto {
    private final Map<String, Map<String, List<Integer>>> dtoCountNewAdvertisement;

    public CountCategoryForPeriodDto(Map<String, Map<String, List<Integer>>> dtoCountNewAdvertisement) {
        this.dtoCountNewAdvertisement = dtoCountNewAdvertisement;
    }

    public Map<String, Map<String, List<Integer>>> getDtoCountNewAdvertisement() {
        return dtoCountNewAdvertisement;
    }
}
