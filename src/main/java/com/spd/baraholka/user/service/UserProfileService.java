package com.spd.baraholka.user.service;

import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.controller.dto.*;
import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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
        Set<Role> roles = userService.getRolesByUserId(id);
        return collectUserDTO(user, roles, additionalResources, blockDetail);
    }

    public UserDTO getCurrentUser() {
        User user = userService.getCurrentUser();
        return getUserById(user.getId());
    }

    public int updateUserGeneralSettings(int id, boolean openAdsInNewTab) {
        return userSettingsService.updateUserGeneralSettings(id, openAdsInNewTab);
    }

    public List<UserShortViewDTO> getAllUsers() {
        List<UserShortViewDTO> users = userService.getAllUsers();
        List<ShortViewBlockDetailDTO> blockDetails = userBlockService.getAllUsersBlockDetails();
        return collectShortViewUserDTO(users, blockDetails);
    }

    public FullBlockDetailDTO blockUser(BlockDetailDTO blockDetailDTO) {
        return userBlockService.blockUser(blockDetailDTO);
    }

    public ShortViewBlockDetailDTO unBlockUser(int userId, boolean isNotify) {
        return userBlockService.unBlockUser(userId, isNotify);
    }

    @Transactional
    public EditUserMainInfoDTO updateUserMainInfo(EditUserMainInfoDTO mainInfoDTO) {
        EditUserMainInfoDTO updatedMainInfoDTO = userService.updateUserInfoPart(mainInfoDTO);
        List<UserAdditionalResourceDTO> updatedResources =
                userAdditionalResourceService.updateResourceInfoPart(mainInfoDTO.getAdditionalContactResources(), mainInfoDTO.getUserId());
        return collectUserMainInfoDTO(updatedMainInfoDTO, updatedResources);
    }

    public Set<Integer> getUserAdditionalResourcesId(int userId) {
        return userAdditionalResourceService.getUserAdditionalResourcesId(userId);
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

    private UserDTO collectUserDTO(UserDTO user, Set<Role> roles, List<UserAdditionalResourceDTO> additionalResources, ShortViewBlockDetailDTO blockDetail) {
        user.setRoles(roles);
        user.setAdditionalContactResources(additionalResources);
        user.setBlocked(blockDetail.isBlocked());
        user.setEndDateOfBan(blockDetail.getBlockedUntil());
        return user;
    }

    private EditUserMainInfoDTO collectUserMainInfoDTO(EditUserMainInfoDTO updatedMainInfoDTO, List<UserAdditionalResourceDTO> updatedResources) {
        updatedMainInfoDTO.setAdditionalContactResources(updatedResources);
        return updatedMainInfoDTO;
    }

    public int updateAvatar(int userId, String imageUrl) {
        return userService.updateAvatar(userId, imageUrl);
    }
}
