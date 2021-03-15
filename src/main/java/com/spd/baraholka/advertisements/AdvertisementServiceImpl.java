package com.spd.baraholka.advertisements;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableScheduling
@Service
public class AdvertisementServiceImpl {
    private static final long TIME_OF_DELETING_ARCHIVED_ADVERTISEMENT = 86400000L;
    private final AdvertisementRepository advertisementRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Scheduled(fixedRate = TIME_OF_DELETING_ARCHIVED_ADVERTISEMENT)
    public void deleteOldArchiveAdvertisements() {
        List<Integer> list = advertisementRepository.getArchivedAdvertisementForDeleting();

        list.forEach(advertisementRepository::updateArchivedStatusToDeleted);
    }
}
