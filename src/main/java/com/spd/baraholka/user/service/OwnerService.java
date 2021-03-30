package com.spd.baraholka.user.service;

import com.spd.baraholka.user.controller.dto.OwnerDTO;
import com.spd.baraholka.user.controller.mappers.OwnerMapper;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.entities.Owner;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private final PersistenceUserService persistenceUserService;
    private final OwnerMapper ownerMapper;

    public OwnerService(PersistenceUserService persistenceUserService, OwnerMapper ownerMapper) {
        this.persistenceUserService = persistenceUserService;
        this.ownerMapper = ownerMapper;
    }

    public OwnerDTO getOwner(int id) {
        Owner owner = persistenceUserService.selectOwner(id);
        return ownerMapper.convertToDTO(owner);
    }
}
