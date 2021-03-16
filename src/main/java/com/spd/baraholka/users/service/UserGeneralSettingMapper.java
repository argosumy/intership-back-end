package com.spd.baraholka.users.service;

import com.spd.baraholka.users.persistance.UserGeneralSetting;
import org.springframework.stereotype.Component;

@Component
public class UserGeneralSettingMapper {

    UserGeneralSetting convertToEntity(UserGeneralSettingDTO userGeneralSettingDTO) {
        UserGeneralSetting userGeneralSetting = new UserGeneralSetting();
        userGeneralSetting.setId(userGeneralSettingDTO.getId());
        userGeneralSetting.setUserId(userGeneralSettingDTO.getUserId());
        userGeneralSetting.setOpenAdsInNewTab(userGeneralSettingDTO.isOpenAdsInNewTab());
        return userGeneralSetting;
    }
}
