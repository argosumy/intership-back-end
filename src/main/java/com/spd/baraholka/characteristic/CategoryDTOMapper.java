package com.spd.baraholka.characteristic;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

@Component
public class CategoryDTOMapper implements RowMapper<CategoryDTO> {
    @Override
    public CategoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CategoryDTO(rs.getString("category_name"),
                (Set<String>) rs.getArray("ar"));
    }
}
