package com.spd.baraholka.views;

import java.util.List;

public interface ViewRepository {

    List<View> read(int userId);

    void save(View view);
}
