package com.spd.baraholka.users.persistance;

import com.spd.baraholka.users.persistance.entities.UserGeneralSetting;

public interface PersistenceUserGeneralSettingService {

    int updateUserGeneralSettings(UserGeneralSetting userGeneralSetting);
}
