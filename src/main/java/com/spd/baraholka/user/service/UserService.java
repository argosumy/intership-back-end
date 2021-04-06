package com.spd.baraholka.user.service;

import com.spd.baraholka.config.exceptions.NotFoundByIdException;
import com.spd.baraholka.login.UserPrincipal;
import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.controller.dto.EditUserMainInfoDTO;
import com.spd.baraholka.user.controller.dto.UserAdditionalResourceDTO;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.controller.mappers.UserAdditionalResourceMapper;
import com.spd.baraholka.user.controller.mappers.UserMapper;
import com.spd.baraholka.user.persistance.PersistenceUserAdditionalResourcesService;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.persistance.entities.UserAdditionalResource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.spd.baraholka.role.Role.MODERATOR;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Qualifier("UserService")
public class UserService implements UserDetailsService {

    private final PersistenceUserService persistenceUserService;
    private final PersistenceUserAdditionalResourcesService persistenceResourceService;
    private final UserMapper userMapper;
    private final UserAdditionalResourceMapper resourceMapper;
    private final Predicate<UserAdditionalResource> isResourceNew = additionalResourceDTO -> additionalResourceDTO.getId() == 0;

    private static final String USER_NOT_FOUND = "User not found";

    public UserService(PersistenceUserService persistenceUserService,
                       PersistenceUserAdditionalResourcesService persistenceResourceService,
                       UserMapper userMapper,
                       UserAdditionalResourceMapper resourceMapper) {
        this.persistenceUserService = persistenceUserService;
        this.persistenceResourceService = persistenceResourceService;
        this.userMapper = userMapper;
        this.resourceMapper = resourceMapper;
    }

    public UserDTO getUserById(int id) {
        Optional<User> user = persistenceUserService.selectUserById(id);
        if (user.isEmpty()) {
            throw new NotFoundByIdException(id);
        } else {
            return collectUserDTO(user.get());
        }
    }

    private UserDTO collectUserDTO(User user) {
        List<UserAdditionalResource> additionalResources = persistenceResourceService.selectUserAdditionalResources(user.getId());
        UserDTO userDTO = userMapper.convertToDTO(user);
        List<UserAdditionalResourceDTO> additionalResourceDTO = resourceMapper.convertToDTOList(additionalResources);
        userDTO.setAdditionalContactResources(additionalResourceDTO);
        Set<Role> roles = findRolesByUserId(user.getId());
        userDTO.setRoles(roles);
        return userDTO;
    }

    private User collectUser(User user) {
        Set<Role> roles = findRolesByUserId(user.getId());
        user.setRoles(roles);
        return user;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username);
        return collectUser(user);
    }

    public Set<Role> findRolesByUserId(int id) {
        return persistenceUserService.findRolesByUserId(id);
    }

    public User findByEmail(String email) {
        Optional<User> optionalUser = persistenceUserService.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        return collectUser(user);
    }

    public User registerNewUser(OAuth2UserDTO oAuth2UserDTO) {
        User user = new User();
        if (!existsByEmail(oAuth2UserDTO.getEmail())) {
            user = convertFromOAuth(oAuth2UserDTO);
            if (count() == 0) {
                user.grantRole(MODERATOR);
            }
            create(user);
        }
        return user;
    }

    public User getCurrentUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findByEmail(userPrincipal.getUsername());
    }

    public UserDTO getCurrentUserDTO() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = findByEmail(userPrincipal.getUsername());
        return collectUserDTO(user);
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

    public int updateAvatar(int userId, String imageUrl) {
        return persistenceUserService.updateAvatar(userId, imageUrl);
    }
}
