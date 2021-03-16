package com.spd.baraholka.users.controller.mappers;

import com.spd.baraholka.users.controller.dto.UserGeneralSettingDTO;
import com.spd.baraholka.users.persistance.entities.UserGeneralSetting;
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
