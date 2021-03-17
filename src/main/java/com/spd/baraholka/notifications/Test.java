package com.spd.baraholka.notifications;

import com.spd.baraholka.notifications.DAO.NotificationDAO;
import com.spd.baraholka.notifications.enume.EventTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Test {
private NotificationDAO notificationDAO;
    @SuppressWarnings("checkstyle:EmptyLineSeparator")
    @Autowired
    public Test(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }
    @GetMapping("/")
    public String test() {
        Map<String, String> arg = new HashMap<>();
        arg.put("sendTo", "2");
        arg.put("reason", "BAD BOY BLUE");
        notificationDAO.saveNotification(EventTypes.ACCOUNT_BAN, arg);
        return "OK";
    }
}
