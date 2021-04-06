package com.spd.baraholka.profile.statistic.enumes;

import java.time.LocalDateTime;
import java.util.*;

public enum PeriodStatistic {
    DAY {
        public Set<List<LocalDateTime>> getPeriods() {
            final int countPeriod = 12;
            final int hoursIntoPeriod = 2;
            return makePeriods(countPeriod, hoursIntoPeriod);
        }
    },
    WEEK {
        public Set<List<LocalDateTime>> getPeriods() {
            final int countPeriod = 7;
            final int hoursIntoPeriod = 24;
            return makePeriods(countPeriod, hoursIntoPeriod);
        }
    },
    MONTH {
        public Set<List<LocalDateTime>> getPeriods() {
            final int countPeriod = 30;
            final int hoursIntoPeriod = 24;
            return makePeriods(countPeriod, hoursIntoPeriod);
        }
    };

    Set<List<LocalDateTime>> makePeriods(int countPeriod, int hoursIntoPeriod) {
        int number = 1;
        Set<List<LocalDateTime>> periods = new LinkedHashSet<>();
        LocalDateTime start = LocalDateTime.now().minusHours(countPeriod * hoursIntoPeriod);
        while (number <= countPeriod) {
            List<LocalDateTime> period = new ArrayList<>();
            LocalDateTime end = start.plusHours(hoursIntoPeriod);
            period.add(start);
            period.add(end);
            periods.add(period);
            start = end;
            number += 1;
        }
        return periods;
    }

    public abstract Set<List<LocalDateTime>> getPeriods();
}