package com.spd.baraholka.profile.statistic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PeriodStatistic {
    DAY {
        public Map<Integer, List<LocalDateTime>> getPeriods() {
            final int countPeriod = 12;
            final int hoursIntoPeriod = 2;
            return makePeriods(countPeriod, hoursIntoPeriod);
        }
    },
    WEEK {
        public Map<Integer, List<LocalDateTime>> getPeriods() {
            final int countPeriod = 7;
            final int hoursIntoPeriod = 24;
            return makePeriods(countPeriod, hoursIntoPeriod);
        }
    },
    MONTH {
        public Map<Integer, List<LocalDateTime>> getPeriods() {
            final int countPeriod = 30;
            final int hoursIntoPeriod = 24;
            return makePeriods(countPeriod, hoursIntoPeriod);
        }
    };

    Map<Integer, List<LocalDateTime>> makePeriods(int countPeriod, int hoursIntoPeriod) {
        int number = 1;
        Map<Integer, List<LocalDateTime>> periods = new HashMap<>();
        LocalDateTime start = LocalDateTime.now().minusHours(countPeriod * hoursIntoPeriod);
        while (number <= countPeriod) {
            List<LocalDateTime> period = new ArrayList<>();
            LocalDateTime end = start.plusHours(hoursIntoPeriod);
            period.add(start);
            period.add(end);
            periods.put(number, period);
            start = end;
            number += 1;
        }
        return periods;
    }

    public abstract Map<Integer, List<LocalDateTime>> getPeriods();
}