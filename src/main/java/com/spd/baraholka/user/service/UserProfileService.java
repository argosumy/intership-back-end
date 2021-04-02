package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.dto.*;
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
        ShortViewBlockDetailDTO blockDetail = userBlockService.getShortViewUserBlockDetail(user.getId());
        return collectUserDTO(user, additionalResources, blockDetail);
    }

    public int updateUserGeneralSettings(int id, boolean openAdsInNewTab) {
        return userSettingsService.updateUserGeneralSettings(id, openAdsInNewTab);
    }

    public List<UserShortViewDTO> getAllUsers() {
        List<UserShortViewDTO> users = userService.getAllUsers();
        List<ShortViewBlockDetailDTO> blockDetails = userBlockService.getAllUsersBlockDetails();
        return collectShortViewUserDTO(users, blockDetails);
    }

    public int blockUser(BlockDetailDTO blockDetailDTO) {
        return userBlockService.blockUser(blockDetailDTO);
    }

    private UserDTO collectUserDTO(UserDTO user, List<UserAdditionalResourceDTO> additionalResources, ShortViewBlockDetailDTO blockDetail) {
        user.setAdditionalContactResources(additionalResources);
        user.setBlocked(blockDetail.isBlocked());
        user.setEndDateOfBan(blockDetail.getBlockedUntil());
        return user;
    }

    //TODO replace with lambda
    private List<UserShortViewDTO> collectShortViewUserDTO(List<UserShortViewDTO> users, List<ShortViewBlockDetailDTO> blockDetails) {
        for (UserShortViewDTO user : users) {
            for (ShortViewBlockDetailDTO blockDetail : blockDetails) {
                if (user.getId() == blockDetail.getUserId()) {
                    user.setBlocked(blockDetail.isBlocked());
                    user.setEndDateOfBan(blockDetail.getBlockedUntil());
                }
            }
        }
        return users;
    }
}
