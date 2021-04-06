package com.spd.baraholka.user.service;

import com.spd.baraholka.login.UserPrincipal;
import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.controller.dto.EditUserMainInfoDTO;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.controller.mappers.UserMapper;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.spd.baraholka.role.Role.MODERATOR;

@Service
@Qualifier("UserService")
public class UserService implements UserDetailsService {

    private final PersistenceUserService persistenceUserService;
    private final UserMapper userMapper;

    public UserService(PersistenceUserService persistenceUserService, UserMapper userMapper) {
        this.persistenceUserService = persistenceUserService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username);
        return collectUser(user);
    }

    public UserDTO getUserById(int id) {
        User user = persistenceUserService.selectUserById(id);
        return userMapper.convertToDTO(user);
    }

    public User getUserEntityById(int id) {
        User user = persistenceUserService.selectUserById(id);
        Set<Role> roles = persistenceUserService.getRolesByUserId(id);
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

    public Set<Role> getRolesByUserId(int id) {
        return persistenceUserService.getRolesByUserId(id);
    }

    public User findByEmail(String email) {
        Optional<User> optionalUser = persistenceUserService.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
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

    public EditUserMainInfoDTO updateUserInfoPart(EditUserMainInfoDTO mainInfoDTO) {
        User user = userMapper.convertToEntity(mainInfoDTO);
        User updatedUserInfo = persistenceUserService.updateUserMainInfo(user);
        return userMapper.convertToInfoDTO(updatedUserInfo);
    }

    private User collectUser(User user) {
        Set<Role> roles = getRolesByUserId(user.getId());
        user.setRoles(roles);
        return user;
    }

    public int updateAvatar(int userId, String imageUrl) {
        return persistenceUserService.updateAvatar(userId, imageUrl);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    public void grantRole(User user, Role role) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(role);
        boolean isRoleGranted = user.grantRole(role);
        if (isRoleGranted) {
            persistenceUserService.saveRole(user.getId(), role.name());
        }
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    public void revokeRole(User user, Role role) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(role);
        boolean isRoleRevoked = user.revokeRole(role);
        if (isRoleRevoked) {
            persistenceUserService.deleteRole(user.getId(), role.name());
        }
    }
}
