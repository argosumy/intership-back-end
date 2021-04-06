package com.spd.baraholka.characteristic.persistance.mappers;

import com.spd.baraholka.characteristic.persistance.entities.CategoryModel;
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
public class CategoryMapper implements RowMapper<CategoryModel> {
    @Override
    public CategoryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Array sourceArray = rs.getArray("ar");
        String[] array = (String[]) sourceArray.getArray();
        Set<String> targetSet = new HashSet<>();
        Collections.addAll(targetSet, array);

        return new CategoryModel(Category.valueOf(rs.getString("category_name")), targetSet);
    }
}
