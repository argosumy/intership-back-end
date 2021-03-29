package com.spd.baraholka.promotion.service;

import com.spd.baraholka.promotion.persistance.repositiries.PromotionAdvertisementRepository;
import org.springframework.stereotype.Service;

@Service
public class PromotionAdvertisementService {
    private final PromotionAdvertisementRepository promoRepository;

    public PromotionAdvertisementService(PromotionAdvertisementRepository promoRepository) {
        this.promoRepository = promoRepository;
    }

    public void promotionAd(int advertisementId) {
        promoRepository.updatePromotionDate(advertisementId);
    }
}
