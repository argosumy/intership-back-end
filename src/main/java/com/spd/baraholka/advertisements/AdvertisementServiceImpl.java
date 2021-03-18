package com.spd.baraholka.advertisements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdvertisementServiceImpl implements AdvertisementService{
    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    @Scheduled(cron = "${baraholka.scheduled.delete-old-archive-task}")
    public void deleteOldArchiveAdvertisements() {
        advertisementRepository.changeStatusArchivedOnDeleted();
    }
}
