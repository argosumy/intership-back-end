package com.spd.baraholka.config;

import com.spd.baraholka.advertisements.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
public class ScheduleConfig {
    private final AdvertisementService advertisementService;

    @Autowired
    public ScheduleConfig(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @Bean
    public void task() {
        advertisementService.deleteOldArchiveAdvertisements();
    }
}
