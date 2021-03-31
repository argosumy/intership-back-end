package com.spd.baraholka.advertisement.controller;

import com.spd.baraholka.advertisement.controller.dto.EditedAdvertisementDTO;
import com.spd.baraholka.advertisement.controller.dto.InitialAdvertisementDTO;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.annotation.advertisement.ChangedStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
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

    @PutMapping("/{id}/promotion")
    public void promotionAd(@PathVariable(value = "id") int adId) {
        advertisementService.promotionAd(adId);
    }
}
