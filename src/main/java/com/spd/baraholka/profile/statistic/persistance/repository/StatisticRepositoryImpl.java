package com.spd.baraholka.profile.statistic.persistance.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class StatisticRepositoryImpl implements StatisticRepository {
    private final JdbcTemplate template;
    private final ResultSetExtractor<Map<String, Integer>> extractor;

    public StatisticRepositoryImpl(JdbcTemplate template) {
        this.template = template;
        extractor = new ResultSetExtractor<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<String, Integer> result = new HashMap<>();
                while (rs.next()) {
                    result.put(rs.getString("category"), rs.getInt("count"));
                }
                return result;
            }
        };
    }

    @Override
    public Map<String, Integer> getCountCategoryByGroup(String sql) {
        Map<String, Integer> resultMap;
        resultMap = template.query(sql, extractor);
        return resultMap;
    }

    @Override
    public Map<String, Integer> getCountCategoryByGroupForPeriod(LocalDateTime start, LocalDateTime end, String sql) {
        Map<String, Integer> resultMap;
        resultMap = template.query(sql, extractor, start, end);
        return resultMap;
    }
}