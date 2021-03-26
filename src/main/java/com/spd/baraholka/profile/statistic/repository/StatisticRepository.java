package com.spd.baraholka.profile.statistic.repository;

import java.util.List;
import java.util.Map;

public interface StatisticRepository {
    Map<String, List<Integer>> getCountByGroupCategory();
    Map<String, List<Integer>> getNewAdvertisementCountForPeriod();
}