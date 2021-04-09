package com.spd.baraholka.views.service;

import com.spd.baraholka.user.service.UserService;
import com.spd.baraholka.views.persistance.entities.View;
import com.spd.baraholka.views.persistance.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewServiceImpl implements ViewService {
    private final ViewRepository viewRepository;
    private final UserService userService;

    @Autowired
    public ViewServiceImpl(ViewRepository viewRepository, UserService userService) {
        this.viewRepository = viewRepository;
        this.userService = userService;
    }

    @Override
    public List<View> read() {
        int userId = userService.getCurrentUser().getId();
        return viewRepository.read(userId);
    }

    @Override
    public void save(int advertisementsId) {
        int userId = userService.getCurrentUser().getId();
        viewRepository.save(userId, advertisementsId);
    }

    @Override
    public int getCountOfViewsForAdvertisement(int advertisementId) {
        return viewRepository.getCountOfViews(advertisementId);
    }
}