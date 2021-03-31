package com.spd.baraholka.ad;

import com.spd.baraholka.advertisement.service.AdvertisementService;
import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class AdvertisementServiceImplTest {

    @SpyBean
    private AdvertisementService advertisementService;

    @Test
    void whenWaitTenSecondsThenScheduledIsCalledAtLeastOneTimes() {
        await()
                .atMost(Duration.TEN_SECONDS)
                .untilAsserted(() -> verify(advertisementService, atLeast(1)).deleteOldArchiveAdvertisements());
    }
}