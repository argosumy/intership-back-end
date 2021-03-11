package com.spd.baraholka.users.persistance;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserGeneralSettingRepository {

    private static final String UPDATE_GENERAL_SETTINGS = "UPDATE users_settings SET open_ads_in_new_tab=? WHERE id=?";
    private final JdbcTemplate jdbcTemplate;

    public UserGeneralSettingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int updateUserGeneralSettings(UserGeneralSetting userGeneralSetting) {
        jdbcTemplate.update(UPDATE_GENERAL_SETTINGS, userGeneralSetting.isOpenAdsInNewTab(), userGeneralSetting.getId());
        return userGeneralSetting.getId();
    }
}
