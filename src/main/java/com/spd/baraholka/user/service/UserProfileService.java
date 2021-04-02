package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.dto.BlockDetailDTO;
import com.spd.baraholka.user.controller.dto.UserAdditionalResourceDTO;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {

    private final UserService userService;
    private final UserAdditionalResourceService userAdditionalResourceService;
    private final UserBlockService userBlockService;
    private final UserSettingsService userSettingsService;

    public UserProfileService(UserService userService,
                              UserAdditionalResourceService userAdditionalResourceService,
                              UserBlockService userBlockService,
                              UserSettingsService userSettingsService) {
        this.userService = userService;
        this.userAdditionalResourceService = userAdditionalResourceService;
        this.userBlockService = userBlockService;
        this.userSettingsService = userSettingsService;
    }

    public UserDTO getUserById(int id) {
        UserDTO user = userService.getUserById(id);
        List<UserAdditionalResourceDTO> additionalResources = userAdditionalResourceService.getUserAdditionalResources(user.getId());
        user.setAdditionalContactResources(additionalResources);
        return user;
    }

    public int updateUserGeneralSettings(int id, boolean openAdsInNewTab) {
        return userSettingsService.updateUserGeneralSettings(id, openAdsInNewTab);
    }

    public List<UserShortViewDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    public int blockUser(BlockDetailDTO blockDetailDTO) {
        return userBlockService.blockUser(blockDetailDTO);
    }
}
