package com.spd.baraholka.advertisements;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class AdvertisementServiceImpl {
    private static final long PERIOD_OF_DELETING_OLD_ARCHIVED_ADVERTISEMENT = 86400000L;
    private final AdvertisementRepository advertisementRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Scheduled(fixedRate = PERIOD_OF_DELETING_OLD_ARCHIVED_ADVERTISEMENT)
    public void deleteOldArchiveAdvertisements() {
        advertisementRepository.changeStatusArchivedOnDeleted();
    }
}
