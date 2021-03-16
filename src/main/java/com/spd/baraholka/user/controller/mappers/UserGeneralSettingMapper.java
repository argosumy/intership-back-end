package com.spd.baraholka.user.controller.mappers;

import com.spd.baraholka.user.controller.dto.UserGeneralSettingDTO;
import com.spd.baraholka.user.persistance.entities.UserGeneralSetting;
import org.springframework.stereotype.Component;

@Component
public class UserGeneralSettingMapper {

    public UserGeneralSetting convertToEntity(UserGeneralSettingDTO userGeneralSettingDTO) {
        UserGeneralSetting userGeneralSetting = new UserGeneralSetting();
        userGeneralSetting.setId(userGeneralSettingDTO.getId());
        userGeneralSetting.setUserId(userGeneralSettingDTO.getUserId());
        userGeneralSetting.setOpenAdsInNewTab(userGeneralSettingDTO.isOpenAdsInNewTab());
        return userGeneralSetting;
    }
}
