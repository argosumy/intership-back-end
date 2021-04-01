package com.spd.baraholka.profile.statistic.service;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    Map<String, Integer> getCountCategoryByGroupFull(String sql);

    Map<String, Map<String, List<Integer>>> getCountCategoryByGroupForPeriod(String sql);
}
