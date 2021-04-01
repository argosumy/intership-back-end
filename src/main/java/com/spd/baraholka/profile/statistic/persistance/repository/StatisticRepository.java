package com.spd.baraholka.profile.statistic.persistance.repository;

import java.time.LocalDateTime;
import java.util.Map;

public interface StatisticRepository {
    Map<String, Integer> getCountByGroupCategory(String sql);

    Map<String, Integer> getCountCategoryByGroupForPeriod(LocalDateTime start, LocalDateTime end, String sql);
}