package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserSettingsService;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserSettingsRepository implements PersistenceUserSettingsService {

    private static final String UPDATE_SETTINGS_SQL = "UPDATE users_settings SET open_ads_in_new_tab=:openAdsInNewTab WHERE id=:id";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserSettingsRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int updateUserGeneralSettings(int id, boolean openAdsInNewTab) {
        jdbcTemplate.update(UPDATE_SETTINGS_SQL, Map.of("openAdsInNewTab", openAdsInNewTab, "id", id));
        return id;
    }
}
