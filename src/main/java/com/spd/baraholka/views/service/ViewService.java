package com.spd.baraholka.views.service;

import com.spd.baraholka.views.persistance.entities.View;

import java.util.List;

public interface ViewService {

    List<View> read();

    void save(int advertisementsId);
}
