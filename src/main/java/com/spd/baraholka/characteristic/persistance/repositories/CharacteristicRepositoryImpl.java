package com.spd.baraholka.characteristic.persistance.repositories;

import com.spd.baraholka.characteristic.persistance.dto.CategoryDTO;
import com.spd.baraholka.characteristic.persistance.CharacteristicRepository;
import com.spd.baraholka.characteristic.persistance.entities.Characteristic;
import com.spd.baraholka.characteristic.persistance.mappers.CategoryDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CharacteristicRepositoryImpl implements CharacteristicRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CategoryDTOMapper categoryDTOMapper;

    @Autowired
    CharacteristicRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, CategoryDTOMapper categoryDTOMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryDTOMapper = categoryDTOMapper;
    }

    @Override
    public int save(Characteristic characteristic) {
        final String sql = "INSERT INTO characteristics(category_name, " +
                "characteristics_name, " +
                "characteristics_value, " +
                "is_approved) " +
                "VALUES (:category_name, :characteristics_name, :characteristics_value, :is_approved) RETURNING id";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("category_name", characteristic.getCategoryName())
                .addValue("characteristics_name", characteristic.getCharacteristicName())
                .addValue("characteristics_value", characteristic.getCharacteristicValue())
                .addValue("is_approved", characteristic.isApproved());

        return jdbcTemplate.update(sql, parameters);
    }

    @Override
    public List<CategoryDTO> read() {
        String sql = "SELECT category_name, array_agg(characteristics_name) AS ar FROM characteristics " +
                "WHERE is_deleted = FALSE AND is_approved = TRUE GROUP BY category_name";

        return jdbcTemplate.query(sql, categoryDTOMapper);
    }
}
