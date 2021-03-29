package com.spd.baraholka.profile.statistic.repository;

import com.spd.baraholka.profile.statistic.PeriodStatistic;

import java.util.List;
import java.util.Map;

public interface StatisticRepository {
    Map<String, List<Integer>> getCountByGroupCategory();

    Map<String, List<Integer>> getNewAdvertisementForPeriod(PeriodStatistic period);

    Map<String, List<Integer>> getCountByCategoryForPeriod(PeriodStatistic period);
}