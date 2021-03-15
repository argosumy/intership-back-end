package com.spd.baraholka.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewServiceImpl implements ViewService{
    private final ViewRepository viewRepository;

    @Autowired
    public ViewServiceImpl(ViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    @Override
    public List<View> read(int userId) {

        return viewRepository.read(userId);
    }

    @Override
    public void save(View view) {
        viewRepository.save(view);
    }
}
