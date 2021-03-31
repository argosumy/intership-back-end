package com.spd.baraholka.profile.statistic.service;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    Map<String, Integer> getCountByGroupCategory();

    Map<String, List<Map<String, Integer>>> getCountNewAdvertisementByCategoryForPeriod();
}
