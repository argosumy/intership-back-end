package com.spd.baraholka.profile.statistic.controller.dto;

import java.util.List;
import java.util.Map;

public class CountNewAdvertisementByCategoryForPeriod {
    private final Map<String, List<Map<String, Integer>>> dtoCountNewAdvertisement;

    public CountNewAdvertisementByCategoryForPeriod(Map<String, List<Map<String, Integer>>> dtoCountNewAdvertisement) {
        this.dtoCountNewAdvertisement = dtoCountNewAdvertisement;
    }

    public Map<String, List<Map<String, Integer>>> getDtoCountNewAdvertisement() {
        return dtoCountNewAdvertisement;
    }
}
