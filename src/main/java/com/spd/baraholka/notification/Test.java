package com.spd.baraholka.notification;

import com.spd.baraholka.notification.controller.NotificationController;
import com.spd.baraholka.notification.enumes.EventTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Test {
    private NotificationController controller;

    @Autowired
    public Test(NotificationController controller) {
        this.controller = controller;
    }
    @GetMapping("/")
    public String test() {
        Map<String, String> args = new HashMap<>();
        args.put("sendTo", "2");
        args.put("reason", "BAD BOY BLUE");
        controller.notification(args, EventTypes.ACCOUNT_BAN);
        return "OK";
    }
}