package com.spd.baraholka.users.services;

import com.spd.baraholka.users.persistance.UserGeneralSetting;
import org.springframework.stereotype.Service;

@Service
public class UserGeneralSettingMapper {

    UserGeneralSetting convertToEntity(UserGeneralSettingDTO userGeneralSettingDTO) {
        UserGeneralSetting userGeneralSetting = new UserGeneralSetting();
        userGeneralSetting.setId(userGeneralSettingDTO.getId());
        userGeneralSetting.setUserId(userGeneralSetting.getUserId());
        userGeneralSetting.setOpenAdsInNewTab(userGeneralSettingDTO.isOpenAdsInNewTab());
        return userGeneralSetting;
    }
}
