package com.spd.baraholka.users.services;

import com.spd.baraholka.users.persistance.PersistenceUserGeneralSettingService;
import com.spd.baraholka.users.persistance.UserGeneralSetting;
import org.springframework.stereotype.Service;

@Service
public class UserGeneralSettingService {

    private final PersistenceUserGeneralSettingService persistenceUserGeneralSettingService;
    private final UserGeneralSettingMapper userGeneralSettingMapper;

    public UserGeneralSettingService(PersistenceUserGeneralSettingService persistenceUserGeneralSettingService,
                                     UserGeneralSettingMapper userGeneralSettingMapper) {
        this.persistenceUserGeneralSettingService = persistenceUserGeneralSettingService;
        this.userGeneralSettingMapper = userGeneralSettingMapper;
    }

    public int updateUserGeneralSettings(UserGeneralSettingDTO userGeneralSettingDTO) {
        UserGeneralSetting userGeneralSetting = userGeneralSettingMapper.convertToEntity(userGeneralSettingDTO);
        return persistenceUserGeneralSettingService.updateUserGeneralSettings(userGeneralSetting);
    }
}
