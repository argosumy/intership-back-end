package com.spd.baraholka.characteristic.persistance.mappers;

import com.spd.baraholka.characteristic.persistance.entities.CategoryDTO;
import com.spd.baraholka.characteristic.persistance.entities.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class CategoryDTOMapper implements RowMapper<CategoryDTO> {
    @Override
    public CategoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        Array sourceArray = rs.getArray("ar");
        String[] array = (String[]) sourceArray.getArray();
        Set<String> targetSet = new HashSet<>();
        Collections.addAll(targetSet, array);

        return new CategoryDTO(Category.valueOf(rs.getString("category_name")), targetSet);
    }
}
