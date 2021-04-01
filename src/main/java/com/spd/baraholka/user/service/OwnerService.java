package com.spd.baraholka.user.service;

import com.spd.baraholka.config.exceptions.NotFoundByIdException;
import com.spd.baraholka.user.controller.dto.OwnerDTO;
import com.spd.baraholka.user.controller.mappers.OwnerMapper;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.entities.Owner;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService {

    private final PersistenceUserService persistenceUserService;
    private final OwnerMapper ownerMapper;

    public OwnerService(PersistenceUserService persistenceUserService, OwnerMapper ownerMapper) {
        this.persistenceUserService = persistenceUserService;
        this.ownerMapper = ownerMapper;
    }

    public OwnerDTO getOwner(int id) {
        Optional<Owner> owner = persistenceUserService.selectOwner(id);
        if (owner.isPresent()) {
            return ownerMapper.convertToDTO(owner.get());
        } else {
            throw new NotFoundByIdException(id);
        }
    }
}
