package com.spd.baraholka.profile.statistic.service;

import com.spd.baraholka.profile.statistic.enumes.PeriodStatistic;
import com.spd.baraholka.profile.statistic.persistance.repository.StatisticRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository repository;

    public StatisticServiceImpl(StatisticRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Integer> getCountByGroupCategory() {
        return repository.getCountByGroupCategory();
    }

    @Override
    public Map<String, List<Map<String, Integer>>> getCountNewAdvertisementByCategoryForPeriod() {
        Map<String, List<Map<String, Integer>>> result = new HashMap<>();
        result.put(PeriodStatistic.DAY.name(), repository.getCountNewAdvertisementByCategoryForPeriod(PeriodStatistic.DAY));
        result.put(PeriodStatistic.WEEK.name(), repository.getCountNewAdvertisementByCategoryForPeriod(PeriodStatistic.WEEK));
        result.put(PeriodStatistic.MONTH.name(), repository.getCountNewAdvertisementByCategoryForPeriod(PeriodStatistic.MONTH));
        return result;
    }
}
