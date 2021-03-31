package com.spd.baraholka.profile.statistic.service;

import com.spd.baraholka.profile.statistic.persistance.repository.StatisticRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository repository;

    public StatisticServiceImpl(StatisticRepository repository) {
        this.repository = repository;
    }

    public Map<String, List<Integer>> getCountByGroupCategory() {
        return repository.getCountByGroupCategory();
    }
}
