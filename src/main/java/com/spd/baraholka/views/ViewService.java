package com.spd.baraholka.views;

import java.util.List;

public interface ViewService {

    List<View> read(int userId);

    int save(int userId, int advertisementsId);
}
