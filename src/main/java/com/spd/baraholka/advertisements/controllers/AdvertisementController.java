package com.spd.baraholka.advertisements.controllers;

import com.spd.baraholka.advertisements.persistance.AdvertisementStatusNotMatchException;
import com.spd.baraholka.advertisements.services.AdvertisementDTO;
import com.spd.baraholka.advertisements.services.AdvertisementService;
import com.spd.baraholka.advertisements.services.AdvertisementStatusMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/advertisements")
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    private final AdvertisementStatusMatcher advertisementStatusMatcher;

    public AdvertisementController(AdvertisementService advertisementService,
                                   AdvertisementStatusMatcher advertisementStatusMatcher) {
        this.advertisementService = advertisementService;
        this.advertisementStatusMatcher = advertisementStatusMatcher;
    }

    @PostMapping
    public int saveAdvertisement(@RequestBody AdvertisementDTO advertisementDTO) {
        if (advertisementDTO == null) {
            throw new NullPointerException();
        }
        return advertisementService.saveAdvertisement(advertisementDTO);
    }

    @PutMapping
    public int updateAdvertisement(@RequestBody AdvertisementDTO advertisementDTO) {
        if (advertisementDTO == null) {
            throw new NullPointerException();
        }
        return advertisementService.updateAdvertisement(advertisementDTO);
    }

    @PutMapping("/{id}/{status}")
    public int updateAdvertisementStatus(@PathVariable int id, @PathVariable String status) {
        if (!advertisementStatusMatcher.isStatusMatch(status)) {
            throw new AdvertisementStatusNotMatchException(status);
        }
        return advertisementService.updateAdvertisementStatus(id, status);
    }
}
