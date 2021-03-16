package com.spd.baraholka.users.persistance;

import com.spd.baraholka.users.persistance.entities.UserGeneralSetting;
import com.spd.baraholka.users.persistance.repositories.UserGeneralSettingRepository;
import org.springframework.stereotype.Service;

@Service
public class UserGeneralSettingDataAdapter implements PersistenceUserGeneralSettingService {

    private final UserGeneralSettingRepository userGeneralSettingRepository;

    public UserGeneralSettingDataAdapter(UserGeneralSettingRepository userGeneralSettingRepository) {
        this.userGeneralSettingRepository = userGeneralSettingRepository;
    }

    @Override
    public int updateUserGeneralSettings(UserGeneralSetting userGeneralSetting) {
        return userGeneralSettingRepository.updateUserGeneralSettings(userGeneralSetting);
    }
}
