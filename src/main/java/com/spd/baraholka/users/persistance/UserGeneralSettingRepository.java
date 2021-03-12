package com.spd.baraholka.users.persistance;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserGeneralSettingRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserGeneralSettingRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int updateUserGeneralSettings(UserGeneralSetting userGeneralSetting) {
        String updateSQL = createUpdateSQL();
        jdbcTemplate.update(updateSQL, Map.of("openAdsInNewTab", userGeneralSetting.isOpenAdsInNewTab(), "id", userGeneralSetting.getId()));
        return userGeneralSetting.getId();
    }

    private String createUpdateSQL() {
        return "UPDATE users_settings SET open_ads_in_new_tab=:openAdsInNewTab WHERE id=:id";
    }
}
