package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private List<UserShortViewDTO> collectShortViewUserDTO(List<UserShortViewDTO> users, List<ShortViewBlockDetailDTO> blockDetails) {
        return users.stream().map(userShortViewDTO -> matchUserWithBlockDetails(userShortViewDTO, blockDetails)).collect(Collectors.toList());
    }

    private UserShortViewDTO matchUserWithBlockDetails(UserShortViewDTO userShortViewDTO, List<ShortViewBlockDetailDTO> blockDetails) {
        Optional<ShortViewBlockDetailDTO> userBlockDetail =
                blockDetails.stream().filter(blockDetailDTO -> blockDetailDTO.getUserId() == userShortViewDTO.getId()).findFirst();
        userBlockDetail.ifPresent(blockDetail -> setUserBlockDetail(userShortViewDTO, blockDetail));
        return userShortViewDTO;
    }

    private void setUserBlockDetail(UserShortViewDTO shortViewDTO, ShortViewBlockDetailDTO blockDetail) {
        shortViewDTO.setBlocked(blockDetail.isBlocked());
        shortViewDTO.setEndDateOfBan(blockDetail.getBlockedUntil());
    }
}
