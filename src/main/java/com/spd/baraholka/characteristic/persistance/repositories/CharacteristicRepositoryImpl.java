package com.spd.baraholka.characteristic.persistance.repositories;

import com.spd.baraholka.characteristic.persistance.entities.CategoryModel;
import com.spd.baraholka.characteristic.persistance.CharacteristicRepository;
import com.spd.baraholka.characteristic.persistance.entities.Characteristic;
import com.spd.baraholka.characteristic.controller.dto.CharacteristicDTO;
import com.spd.baraholka.characteristic.persistance.mappers.CategoryMapper;
import com.spd.baraholka.characteristic.persistance.mappers.CharacteristicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CharacteristicRepositoryImpl implements CharacteristicRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CategoryMapper categoryMapper;
    private final CharacteristicMapper characteristicMapper;

    @Autowired
    CharacteristicRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate,
                                 CategoryMapper categoryMapper,
                                 CharacteristicMapper characteristicMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryMapper = categoryMapper;
        this.characteristicMapper = characteristicMapper;
    }

    @Override
    public void save(int adId, CharacteristicDTO characteristic) {
        final String sql = "INSERT INTO characteristics(advertisement_id, category_name, " +
                "characteristics_name, " +
                "characteristics_value, " +
                "is_approved) " +
                "VALUES (:advertisement_id, :category_name, :characteristics_name, :characteristics_value, :is_approved) ";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("advertisement_id", adId)
                .addValue("category_name", characteristic.getCategory().toString())
                .addValue("characteristics_name", characteristic.getCharacteristicName())
                .addValue("characteristics_value", characteristic.getCharacteristicValue())
                .addValue("is_approved", characteristic.getIsApproved());

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public void updateApproveStatus(int id) {
        String sql = "UPDATE characteristics SET is_approved = TRUE " +
                "WHERE id = :id AND is_deleted = FALSE";

        jdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    @Override
    public void delete(int id) {
        String sql = "UPDATE characteristics SET is_deleted = TRUE " +
                "WHERE advertisement_id = :advertisement_id";
        jdbcTemplate.update(sql, new MapSqlParameterSource("advertisement_id", id));
    }

    @Override
    public List<CategoryModel> readAllCategoryWithCharacteristics() {
        String sql = "SELECT category_name, array_agg(characteristics_name) AS ar FROM characteristics " +
                "WHERE is_deleted = FALSE AND is_approved = TRUE GROUP BY category_name";

        return jdbcTemplate.query(sql, categoryMapper);
    }

    @Override
    public List<Characteristic> readForAdId(int adId) {
        String sql = "SELECT * FROM characteristics " +
                "WHERE is_deleted = FALSE AND id = :id";

        return jdbcTemplate.query(sql, new MapSqlParameterSource("id", adId), characteristicMapper);
    }

    @Override
    public List<Characteristic> readNotApproved() {
        String sql = "SELECT * FROM characteristics " +
                "WHERE is_deleted = FALSE AND is_approved = FALSE";

        return jdbcTemplate.query(sql, characteristicMapper);
    }
}
