package com.spd.baraholka.users.persistance;

import org.springframework.stereotype.Service;

@Service
public class UserGeneralSettingDataAdapter implements PersistenceUserGeneralSettingService {

    private final UserGeneralSettingRepository userGeneralSettingRepository;

    public UserGeneralSettingDataAdapter(UserGeneralSettingRepository userGeneralSettingRepository) {
        this.userGeneralSettingRepository = userGeneralSettingRepository;
    }
}
