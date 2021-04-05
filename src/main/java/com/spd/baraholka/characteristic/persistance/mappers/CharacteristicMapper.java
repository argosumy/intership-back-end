package com.spd.baraholka.characteristic.persistance.mappers;

import com.spd.baraholka.characteristic.persistance.entities.Category;
import com.spd.baraholka.characteristic.persistance.entities.Characteristic;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CharacteristicMapper implements RowMapper<Characteristic> {
    @Override
    public Characteristic mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Characteristic(
                rs.getInt("id"),
                rs.getInt("advertisement_id"),
                Category.valueOf(rs.getString("category_name")),
                rs.getString("characteristics_name"),
                rs.getString("characteristics_value"),
                rs.getBoolean("is_approved")
        );
    }
}