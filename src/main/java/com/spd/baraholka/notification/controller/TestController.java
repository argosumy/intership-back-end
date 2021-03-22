package com.spd.baraholka.notification.controller;

import com.spd.baraholka.notification.enumes.EventTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
    private NotificationController controller;
    @SuppressWarnings("checkstyle:EmptyLineSeparator")
    @Autowired
    public TestController(NotificationController controller) {
        this.controller = controller;
    }
    @SuppressWarnings("checkstyle:EmptyLineSeparator")
    @PostConstruct
    public void startTest() {
        Map<String, String> args = new HashMap<>();
        args.put("sendTo", "1");
        args.put("reason", "BAD BOY");
        args.put("sendTo", "1");
        args.put("adId", "1");
        System.out.println("START APP");
        controller.notification(args, EventTypes.CHANGES_ADVERTISEMENT);
    }
}