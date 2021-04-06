package com.spd.baraholka.user.service;

import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.user.controller.dto.EditUserMainInfoDTO;
import com.spd.baraholka.user.controller.dto.UserAdditionalResourceDTO;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.controller.mappers.UserAdditionalResourceMapper;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.controller.mappers.UserMapper;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final PersistenceUserService persistenceUserService;
    private final UserMapper userMapper;
    private final UserAdditionalResourceMapper resourceMapper;
    private final Predicate<UserAdditionalResource> isResourceNew = additionalResourceDTO -> additionalResourceDTO.getId() == 0;

    public UserService(PersistenceUserService persistenceUserService, UserMapper userMapper) {
        this.persistenceUserService = persistenceUserService;
        this.userMapper = userMapper;
    }

    public UserDTO getUserById(int id) {
        User user = persistenceUserService.selectUserById(id);
        return userMapper.convertToDTO(user);
    }

    public List<UserShortViewDTO> getAllUsers() {
        List<User> users = persistenceUserService.selectAllUsers();
        return userMapper.convertToDTOList(users);
    }

    public void create(User user) {
        persistenceUserService.create(user);
    }

    public boolean existsByEmail(String email) {
        Optional<Boolean> isExist = persistenceUserService.existsByEmail(email);
        return isExist.orElse(false);
    }

    public int count() {
        Optional<Integer> count = persistenceUserService.count();
        return count.orElse(0);
    }

    public User convertFromOAuth(OAuth2UserDTO oAuth2UserDto) {
        return userMapper.convertFromOAuth(oAuth2UserDto);
    }

    public boolean isUserExist(int id) {
        Optional<Boolean> exist = persistenceUserService.isExist(id);
        return exist.orElse(false);
    }

    public Set<Integer> getUserAdditionalResourcesId(int userId) {
        List<Integer> resourcesId = persistenceResourceService.selectUserAdditionalResourcesId(userId);
        return new HashSet<>(resourcesId);
    }

    @Transactional
    public EditUserMainInfoDTO updateUserMainInfo(EditUserMainInfoDTO mainInfoDTO) {
        User user = userMapper.convertToEntity(mainInfoDTO);
        List<UserAdditionalResource> allResources = resourceMapper.convertToEntityList(mainInfoDTO.getAdditionalContactResources(), user.getId());

        EditUserMainInfoDTO updatedMainInfoDTO = updateUserInfoPart(user);
        List<UserAdditionalResourceDTO> updatedResources = updateResourceInfoPart(allResources, user.getId());

        return collectUserMainInfoDTO(updatedMainInfoDTO, updatedResources);
    }

    private EditUserMainInfoDTO updateUserInfoPart(User user) {
        User updatedUserInfo = persistenceUserService.updateUserMainInfo(user);
        return userMapper.convertToInfoDTO(updatedUserInfo);
    }

    private List<UserAdditionalResourceDTO> updateResourceInfoPart(List<UserAdditionalResource> allResources, int userId) {
        Map<Boolean, List<UserAdditionalResource>> dividedResources = divideByExist(allResources);
        persistenceResourceService.updateUserAdditionalResources(dividedResources.getOrDefault(false, Collections.emptyList()));
        persistenceResourceService.insertNewUserAdditionalResources(dividedResources.getOrDefault(true, Collections.emptyList()));
        return collectUserAdditionalResourcesDTO(userId);
    }

    private Map<Boolean, List<UserAdditionalResource>> divideByExist(List<UserAdditionalResource> resources) {
        return resources.stream().collect(Collectors.groupingBy(isResourceNew::test, Collectors.toList()));
    }

    private List<UserAdditionalResourceDTO> collectUserAdditionalResourcesDTO(int userId) {
        List<UserAdditionalResource> additionalResources = persistenceResourceService.selectUserAdditionalResources(userId);
        return resourceMapper.convertToDTOList(additionalResources);
    }

    private EditUserMainInfoDTO collectUserMainInfoDTO(EditUserMainInfoDTO updatedMainInfoDTO, List<UserAdditionalResourceDTO> updatedResources) {
        updatedMainInfoDTO.setAdditionalContactResources(updatedResources);
        return updatedMainInfoDTO;
    }
}
