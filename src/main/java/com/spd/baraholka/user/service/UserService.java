package com.spd.baraholka.user.service;

import com.spd.baraholka.config.exceptions.NotFoundByIdException;
import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.user.controller.dto.UserAdditionalResourceDTO;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.controller.mappers.UserAdditionalResourceMapper;
import com.spd.baraholka.user.controller.mappers.UserMapper;
import com.spd.baraholka.user.persistance.PersistenceUserAdditionalResourcesService;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.persistance.entities.UserAdditionalResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final PersistenceUserService persistenceUserService;
    private final PersistenceUserAdditionalResourcesService persistenceResourceService;
    private final UserMapper userMapper;
    private final UserAdditionalResourceMapper resourceMapper;

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
}
