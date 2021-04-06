package com.spd.baraholka.views.controller;

import com.spd.baraholka.views.persistance.entities.View;
import com.spd.baraholka.views.service.ViewService;
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

    @GetMapping
    public List<View> getAllViews() {
        return viewService.read();
    }
}
