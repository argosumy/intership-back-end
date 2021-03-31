package com.spd.baraholka.user.service;

import com.spd.baraholka.user.persistance.PersistenceUserSettingsService;
import org.springframework.stereotype.Service;

@Service
public class UserSettingsService {

    private final PersistenceUserSettingsService persistenceUserSettingsService;

    public UserSettingsService(PersistenceUserSettingsService persistenceUserSettingsService) {
        this.persistenceUserSettingsService = persistenceUserSettingsService;
    }

    public int updateUserGeneralSettings(int id, boolean openAdsInNewTab) {
        return persistenceUserSettingsService.updateUserGeneralSettings(id, openAdsInNewTab);
    }
}
