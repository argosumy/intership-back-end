package com.spd.baraholka.views.persistance.repository;

import com.spd.baraholka.views.persistance.entities.View;

import java.util.List;

public interface ViewRepository {

    List<View> read(int userId);

    void save(int userId, int advertisementsId);
}
