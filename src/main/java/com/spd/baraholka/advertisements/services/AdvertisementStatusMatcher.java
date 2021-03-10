package com.spd.baraholka.advertisements.services;

import com.spd.baraholka.advertisements.persistance.AdvertisementStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AdvertisementStatusMatcher {

    public boolean isStatusMatch(String supposedStatus) {
        final AdvertisementStatus[] values = AdvertisementStatus.values();
        return Arrays.stream(values).anyMatch(status -> status.name().equals(supposedStatus));
    }
}
