package com.spd.baraholka.notification.controller;

import com.spd.baraholka.notification.DAO.NotificationDAO;
import com.spd.baraholka.notification.enumes.EventTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.Map;

@Controller
public class NotificationController {
    private NotificationDAO notificationDAO;

    @Autowired
    public NotificationController(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    @SuppressWarnings("checkstyle:EmptyLineSeparator")
    public void notification(Map<String, String> args, EventTypes eventTypes) {
        saveNotification(args, eventTypes);
    }

    private void saveNotification(Map<String, String> args, EventTypes eventTypes) {
        notificationDAO.saveNotification(eventTypes, args);
    }
}