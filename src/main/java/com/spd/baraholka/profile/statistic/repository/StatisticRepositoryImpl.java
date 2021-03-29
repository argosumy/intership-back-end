package com.spd.baraholka.profile.statistic.repository;

import com.spd.baraholka.profile.statistic.PeriodStatistic;
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
    public Map<String, List<Integer>> getCountByGroupCategory() {
        Map<String, List<Integer>> resultMap = new HashMap<>();
        resultMap = template.query("SELECT ad.category, count(*)" +
                                       "FROM history_of_views INNER JOIN advertisement AS ad ON history_of_views.ad_id = ad.id" +
                                       "GROUP BY ad.category", new ResultSetExtractor<Map<String, List<Integer>>>() {
            @Override
            public Map<String, List<Integer>> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<String, List<Integer>> rsMapExtract = new HashMap<>();
                while (rs.next()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(rs.getInt("count"));
                    rsMapExtract.put(rs.getString("category"), list);
                }
                return rsMapExtract;
            }
        });
        return resultMap;
    }

    @Override
    public Map<String, List<Integer>> getNewAdvertisementForPeriod(PeriodStatistic period) {
        LocalDateTime start = period.getStartEnd().get("START");
        LocalDateTime end = period.getStartEnd().get("END");
        Map<String, List<Integer>> resultMap = new HashMap<>();
        resultMap = resultMap = template.query("SELECT count(*) FROM advertisementes AS ad WHERE ad.created_at ",
                new ResultSetExtractor<Map<String, List<Integer>>>() {
                    @Override
                    public Map<String, List<Integer>> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        Map<String, List<Integer>> rsMapExtract = new HashMap<>();
                        while (rs.next()) {
                            List<Integer> list = new ArrayList<>();
                            list.add(rs.getInt("count"));
                            rsMapExtract.put(rs.getString("category"), list);
                        }
                        return rsMapExtract;
                    }
                }, start, end);
        return null;
    }

    @Override
    public Map<String, List<Integer>> getCountByCategoryForPeriod(PeriodStatistic period) {
        Map<Integer, List<LocalDateTime>> realPeriod = period.getPeriods();
        Map<String, List<Integer>> result = new HashMap<>();
        String sql = "";
        for (Map.Entry<Integer, List<LocalDateTime>> entry : realPeriod.entrySet()) {
            LocalDateTime start = entry.getValue().get(1);
            LocalDateTime end = entry.getValue().get(2);
            template.query("SELECT ad.category, count(*)" +
                    "FROM history_of_views INNER JOIN advertisements AS ad ON history_of_views.ad_id = ad.id" +
                    "WHERE " +
                    "GROUP BY ad.category", new ResultSetExtractor<Map<String, List<Integer>>>() {
                @Override
                public Map<String, List<Integer>> extractData(ResultSet rs) throws SQLException, DataAccessException {
                    Map<String, List<Integer>> rsMapExtract = new HashMap<>();
                    while (rs.next()) {
                        List<Integer> list = new ArrayList<>();
                        list.add(rs.getInt("count"));
                        rsMapExtract.put(rs.getString("category"), list);
                    }
                    return rsMapExtract;
                }
            });

        }
        template.queryForList(sql, "");
        return null;
    }
}
