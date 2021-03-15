package com.spd.baraholka.views;

import java.util.List;

public interface ViewService {

    List<View> read(int userId);

    void save(View view);
}
