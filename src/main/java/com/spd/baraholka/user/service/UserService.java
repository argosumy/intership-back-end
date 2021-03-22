package com.spd.baraholka.user.service;

import com.spd.baraholka.config.exceptions.NotFoundByIdException;
import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.controller.dto.UserAdditionalResourceDTO;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.controller.mappers.UserAdditionalResourceMapper;
import com.spd.baraholka.user.controller.mappers.UserMapper;
import com.spd.baraholka.user.persistance.PersistenceUserAdditionalResourcesService;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.persistance.entities.UserAdditionalResource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.spd.baraholka.role.Role.ROLE_MODERATOR;

@Service
@Qualifier("UserService")
public class UserService implements UserDetailsService {

    private final PersistenceUserService persistenceUserService;
    private final PersistenceUserAdditionalResourcesService persistenceResourceService;
    private final UserMapper userMapper;
    private final UserAdditionalResourceMapper resourceMapper;

    private static final String USER_NOT_FOUND = "User not found";

    public UserService(PersistenceUserService persistenceUserService,
                       PersistenceUserAdditionalResourcesService persistenceResourceService,
                       UserMapper userMapper, UserAdditionalResourceMapper resourceMapper) {
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
        return userDTO;
    }

    public void create(User user) {
        persistenceUserService.create(user);
    }

    public boolean existsByEmail(String email) {
        return persistenceUserService.existsByEmail(email);
    }

    public int count() {
        return persistenceUserService.count();
    }

    public User convertFromOAuth(OAuth2UserDTO oAuth2UserDto) {
        return userMapper.convertFromOAuth(oAuth2UserDto);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username);
        Set<Role> authorities = findRolesByUserId(user.getId());
        user.setRoles(authorities);
        return user;
    }

    public Set<Role> findRolesByUserId(int id) {
        return persistenceUserService.findRolesByUserId(id);
    }

    public User findByEmail(String email) {
        Optional<User> optionalUser = persistenceUserService.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        Set<Role> roles = findRolesByUserId(user.getId());
        user.setRoles(roles);
        return user;
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return new ArrayList<>(authorities);
    }

    public User registerNewUser(OAuth2UserDTO oAuth2UserDTO) {
        User user = new User();
//        if (!isEmailDomainInAllowedDomains(oAuth2UserDTO.getEmail(), allowedDomains)) {
//            throw new BadCredentialsException(DOMAIN_NOT_ALLOWED);
//        }
        if (!existsByEmail(oAuth2UserDTO.getEmail())) {
            user = convertFromOAuth(oAuth2UserDTO);
            if (count() == 0) {
                user.grantRole(ROLE_MODERATOR);
            }
            create(user);
        }
        return user;
    }
}
