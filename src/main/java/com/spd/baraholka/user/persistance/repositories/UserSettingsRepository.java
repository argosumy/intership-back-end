package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserSettingsService;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Map;

@Repository
public class UserSettingsRepository implements PersistenceUserSettingsService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserSettingsRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int updateUserGeneralSettings(int id, boolean openAdsInNewTab) {
        String updateSQL = createUpdateSQL();
        Map<String, ? extends Serializable> updateParameters = createUpdateUserGeneralSettingsParameters(id, openAdsInNewTab);
        jdbcTemplate.update(updateSQL, updateParameters);
        return id;
    }

    private Map<String, ? extends Serializable> createUpdateUserGeneralSettingsParameters(int id, boolean openAdsInNewTab) {
        return Map.of("openAdsInNewTab", openAdsInNewTab, "id", id);
    }

    private String createUpdateSQL() {
        return "UPDATE users_settings SET open_ads_in_new_tab=:openAdsInNewTab WHERE id=:id";
    }
}
