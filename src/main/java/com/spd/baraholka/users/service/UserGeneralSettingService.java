package com.spd.baraholka.users.service;

import com.spd.baraholka.users.controller.dto.UserGeneralSettingDTO;
import com.spd.baraholka.users.controller.mappers.UserGeneralSettingMapper;
import com.spd.baraholka.users.persistance.PersistenceUserGeneralSettingService;
import com.spd.baraholka.users.persistance.entities.UserGeneralSetting;
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
