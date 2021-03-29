package com.spd.baraholka.promotion.controller;

import com.spd.baraholka.promotion.service.PromotionAdvertisementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotion/{id}")
public class PromotionAdvertisementController {
    private final PromotionAdvertisementService service;

    public PromotionAdvertisementController(PromotionAdvertisementService service) {
        this.service = service;
    }

    @PutMapping
    public void promotionAd(@PathVariable(value = "id") int adId) {
        service.promotionAd(adId);
    }
}
