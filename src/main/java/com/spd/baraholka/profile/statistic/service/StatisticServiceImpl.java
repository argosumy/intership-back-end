package com.spd.baraholka.profile.statistic.service;

import com.spd.baraholka.profile.statistic.enumes.PeriodStatistic;
import com.spd.baraholka.profile.statistic.persistance.repository.SQLQueries;
import com.spd.baraholka.profile.statistic.persistance.repository.StatisticRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository repository;

    public StatisticServiceImpl(StatisticRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Integer> getCountCategoryByGroupFull(String sql) {
        return repository.getCountCategoryByGroup(sql);
    }

    @Override
    public Map<String, Map<String, List<Integer>>> getCountCategoryByGroupForPeriod(String sql) {
        Map<String, Map<String, List<Integer>>> result = new HashMap<>();
        result.put(PeriodStatistic.DAY.name(), parser(getCountCategoryByGroupForPeriod(PeriodStatistic.DAY, sql)));
        result.put(PeriodStatistic.WEEK.name(), parser(getCountCategoryByGroupForPeriod(PeriodStatistic.WEEK, sql)));
        result.put(PeriodStatistic.MONTH.name(), parser(getCountCategoryByGroupForPeriod(PeriodStatistic.MONTH, sql)));
        return result;
    }

    private Map<String, List<Integer>> parser(List<Map<String, Integer>> listMapCategoryCount) {
        Map<String, List<Integer>> result = new HashMap<>();
        Set<String> categories = listMapCategoryCount.get(0).keySet();
        for (String category : categories) {
            for (Map<String, Integer> map : listMapCategoryCount) {
                int count = map.get(category);
                List<Integer> list = result.get(category);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(count);
                result.put(category, list);
            }
        }
        return result;
    }

    private List<Map<String, Integer>> getCountCategoryByGroupForPeriod(PeriodStatistic period, String sql) {
        List<Map<String, Integer>> result = new ArrayList<>();
        Map<String, Integer> allCategory = repository.getCountCategoryByGroup(SQLQueries.GET_COUNT_CATEGORY_VIEW_FULL);
        for (List<LocalDateTime> listPeriod : period.getPeriods()) {
            Map<String, Integer> categoryForPeriod;
            categoryForPeriod = repository.getCountCategoryByGroupForPeriod(listPeriod.get(0), listPeriod.get(1), sql);
            for (String category : allCategory.keySet()) {
                if (!categoryForPeriod.containsKey(category)) {
                    categoryForPeriod.put(category, 0);
                }
            }
            result.add(categoryForPeriod);
        }
        return result;
    }
}
