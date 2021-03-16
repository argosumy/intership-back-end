package com.spd.baraholka.user.persistance;

import com.spd.baraholka.user.persistance.entities.UserGeneralSetting;
import com.spd.baraholka.user.persistance.repositories.UserGeneralSettingRepository;
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
