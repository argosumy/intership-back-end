package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.UserAdditionalResourceDTO;
import com.spd.baraholka.user.controller.UserAdditionalResourceMapper;
import com.spd.baraholka.user.controller.UserDTO;
import com.spd.baraholka.user.controller.UserMapper;
import com.spd.baraholka.user.persistance.PersistenceUserAdditionalResourcesService;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.User;
import com.spd.baraholka.user.persistance.UserAdditionalResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        User user = persistenceUserService.selectUserById(id);
        List<UserAdditionalResource> additionalResources = persistenceResourceService.selectUserAdditionalResources(id);
        UserDTO userDTO = userMapper.convertToDTO(user);
        List<UserAdditionalResourceDTO> additionalResourceDTOS = resourceMapper.convertToDTOList(additionalResources);
        userDTO.setAdditionalContactResources(additionalResourceDTOS);
        return userDTO;
    }

    @Transactional
    public int updateUserMainInfo(UserDTO userDTO) {
        User user = userMapper.convertToEntity(userDTO);
        List<UserAdditionalResource> additionalResources =
                resourceMapper.convertToEntityList(userDTO.getAdditionalContactResources());
        persistenceResourceService.updateUserAdditionalResources(additionalResources);
        return persistenceUserService.updateUserMainInfo(user);
    }
}
