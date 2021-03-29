package com.spd.baraholka.advertisement.controller;

import com.spd.baraholka.advertisement.controller.dto.EditedAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.FullAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.InitialAdvertisementDTO;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.annotation.advertisement.AdvertisementExist;
import com.spd.baraholka.annotation.advertisement.ChangedStatus;
import com.spd.baraholka.user.controller.dto.OwnerDTO;
import com.spd.baraholka.user.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    private final UserService userService;

    public AdvertisementController(AdvertisementService advertisementService, UserService userService) {
        this.advertisementService = advertisementService;
        this.userService = userService;
    }

    @PostMapping
    public int saveAdvertisement(@RequestBody @Valid InitialAdvertisementDTO advertisementDTO) {
        return advertisementService.saveAdvertisement(advertisementDTO);
    }

    @PutMapping
    public int updateAdvertisement(@RequestBody @Valid EditedAdvertisementDTO advertisementDTO) {
        return advertisementService.updateAdvertisement(advertisementDTO);
    }

    @PutMapping("/{id}")
    public int updateAdvertisementStatus(@PathVariable int id, @RequestParam("status") @ChangedStatus AdvertisementStatus status) {
        return advertisementService.updateAdvertisementStatus(id, status);
    }

    @GetMapping("/{id}")
    public FullAdvertisementDTO getAdvertisement(@PathVariable("id") @AdvertisementExist int id) {
        FullAdvertisementDTO advertisementDTO = advertisementService.getAdvertisementById(id);
        OwnerDTO ownerDTO = userService.getOwner(id);
        advertisementDTO.setAdvertisementOwner(ownerDTO);
        return advertisementDTO;
    }
}
