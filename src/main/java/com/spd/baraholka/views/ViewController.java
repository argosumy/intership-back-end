package com.spd.baraholka.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/views")
public class ViewController {
    private final ViewService viewService;

    @Autowired
    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping("/{userId}")
    public List<View> getAllViews(@PathVariable int userId) {
        return viewService.read(userId);
    }
}
