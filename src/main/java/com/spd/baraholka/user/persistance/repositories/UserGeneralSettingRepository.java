package com.spd.baraholka.user.persistance.repositories;

import com.spd.baraholka.user.persistance.PersistenceUserGeneralSettingService;
import com.spd.baraholka.user.persistance.entities.UserGeneralSetting;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Map;

@Repository
public class UserGeneralSettingRepository implements PersistenceUserGeneralSettingService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserGeneralSettingRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int updateUserGeneralSettings(UserGeneralSetting userGeneralSetting) {
        String updateSQL = createUpdateSQL();
        Map<String, ? extends Serializable> updateParameters = createUpdateUserGeneralSettingsParameters(userGeneralSetting);
        jdbcTemplate.update(updateSQL, updateParameters);
        return userGeneralSetting.getUserId();
    }

    private Map<String, ? extends Serializable> createUpdateUserGeneralSettingsParameters(UserGeneralSetting userGeneralSetting) {
        return Map.of("openAdsInNewTab", userGeneralSetting.isOpenAdsInNewTab(), "id", userGeneralSetting.getUserId());
    }

    private String createUpdateSQL() {
        return "UPDATE users_settings SET open_ads_in_new_tab=:openAdsInNewTab WHERE id=:id";
    }
}
