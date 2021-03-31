package com.spd.baraholka.profile.statistic.persistance.repository;

import com.spd.baraholka.profile.statistic.enumes.PeriodStatistic;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StatisticRepositoryImpl implements StatisticRepository {
    private final JdbcTemplate template;

    public StatisticRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Map<String, Integer> getCountByGroupCategory() {
        Map<String, Integer> resultMap;
        resultMap = template.query("SELECT category, count(*) FROM advertisements GROUP BY category", new ResultSetExtractor<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<String, Integer> result = new HashMap<>();
                while (rs.next()) {
                    result.put(rs.getString("category"), rs.getInt("count"));
                }
                return result;
            }
        });
        return resultMap;
    }

    @Override
    public List<Map<String, Integer>> getCountNewAdvertisementByCategoryForPeriod(PeriodStatistic period) {
        List<Map<String, Integer>> result = new ArrayList<>();
        for (List<LocalDateTime> listPeriod : period.getPeriods()) {
            result.add(getCountByGroupCategoryForPeriod(listPeriod.get(0), listPeriod.get(1)));
        }
        return result;
    }

    private Map<String, Integer> getCountByGroupCategoryForPeriod(LocalDateTime start, LocalDateTime end) {
        Map<String, Integer> resultMap;
        resultMap = template.query("SELECT category, count(*) " +
                "FROM advertisements " +
                "WHERE publication_date >= ? AND publication_date < ? " +
                "GROUP BY category", new ResultSetExtractor<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<String, Integer> result = new HashMap<>();
                while (rs.next()) {
                    result.put(rs.getString("category"), rs.getInt("count"));
                }
                return result;
            }
        }, start, end);
        return resultMap;
    }

    @Override
    public Map<String, List<Integer>> getCountByCategoryForPeriod(PeriodStatistic period) {
//        Map<Integer, List<LocalDateTime>> realPeriod = period.getPeriods();
//        Map<String, List<Integer>> result = new HashMap<>();
//        String sql = "";
//        for (Map.Entry<Integer, List<LocalDateTime>> entry : realPeriod.entrySet()) {
//            LocalDateTime start = entry.getValue().get(1);
//            LocalDateTime end = entry.getValue().get(2);
//            template.query("SELECT ad.category, count(*)" +
//                    "FROM history_of_views INNER JOIN advertisements AS ad ON history_of_views.ad_id = ad.id" +
//                    "WHERE " +
//                    "GROUP BY ad.category", new ResultSetExtractor<Map<String, List<Integer>>>() {
//                @Override
//                public Map<String, List<Integer>> extractData(ResultSet rs) throws SQLException, DataAccessException {
//                    Map<String, List<Integer>> rsMapExtract = new HashMap<>();
//                    while (rs.next()) {
//                        List<Integer> list = new ArrayList<>();
//                        list.add(rs.getInt("count"));
//                        rsMapExtract.put(rs.getString("category"), list);
//                    }
//                    return rsMapExtract;
//                }
//            });
//        }
   //     template.queryForList(sql, "");
        return null;
    }
}
