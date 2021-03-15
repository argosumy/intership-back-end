package com.spd.baraholka.views;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ViewMapper implements RowMapper<View> {

    @Override
    public View mapRow(ResultSet rs, int rowNum) throws SQLException {
        View view = new View();
        view.setId(rs.getInt("id"));
        view.setId(rs.getInt("user_id"));
        view.setId(rs.getInt("advertisements_id"));
        view.setViewedAt(rs.getTimestamp("viewed_at").toLocalDateTime());

        return view;
    }
}
