package com.spd.baraholka.profile.statistic.service;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    Map<String, Integer> getCountByGroupCategoryFull(String sql);

    Map<String, Map<String, List<Integer>>> getCountNewAdvertisementByCategoryForPeriod(String sql);
}
