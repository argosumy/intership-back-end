package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.persistance.entities.UserGeneralSetting;

public interface PersistenceUserGeneralSettingService {

    int updateUserGeneralSettings(UserGeneralSetting userGeneralSetting);
}
