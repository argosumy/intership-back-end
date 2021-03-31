package com.spd.baraholka.profile.statistic.persistance.repository;

import com.spd.baraholka.profile.statistic.enumes.PeriodStatistic;

import java.util.List;
import java.util.Map;

public interface StatisticRepository {
    Map<String, Integer> getCountByGroupCategory();

    List<Map<String, Integer>> getCountNewAdvertisementByCategoryForPeriod(PeriodStatistic period);

    Map<String, List<Integer>> getCountByCategoryForPeriod(PeriodStatistic period);
}