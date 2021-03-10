package com.spd.baraholka.advertisements.controllers;

import com.spd.baraholka.advertisements.services.AdvertisementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/advertisements")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }
}
