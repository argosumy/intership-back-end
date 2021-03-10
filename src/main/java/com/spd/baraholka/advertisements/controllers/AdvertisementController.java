package com.spd.baraholka.advertisements.controllers;

import com.spd.baraholka.advertisements.services.AdvertisementDTO;
import com.spd.baraholka.advertisements.services.AdvertisementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/advertisements")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping("/save")
    public int saveAdvertisement(@RequestBody AdvertisementDTO advertisementDTO) {
        if (advertisementDTO == null) {
            throw new NullPointerException();
        }
        return advertisementService.saveAdvertisement(advertisementDTO);
    }
}
