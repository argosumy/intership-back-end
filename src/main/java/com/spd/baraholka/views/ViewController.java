package com.spd.baraholka.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/views")
public class ViewController {
    private final ViewService viewService;

    @Autowired
    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @PutMapping("/{userId}/{advertisementsId}")
    public void saveWishlist(@PathVariable int userId, @PathVariable int advertisementsId) {
        View view = new View();
        view.setUserId(userId);
        view.setAdvertisementId(advertisementsId);

        viewService.save(view);
    }

    @GetMapping("/{userId}")
    public List<View> getAllViews(@PathVariable int userId) {

        return viewService.read(userId);
    }
}
