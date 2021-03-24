package com.spd.baraholka.notification.controller;

import com.spd.baraholka.notification.repository.NotificationDAO;
import com.spd.baraholka.notification.enums.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/notifications")
public class NotificationController {
    private final NotificationDAO notificationDAO;

    @Autowired
    public NotificationController(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    public void notification(Map<String, String> args, EventType eventType) {
        saveNotification(args, eventType);
    }

    @PostMapping
    private void saveNotification(Map<String, String> args, EventType eventType) {
        notificationDAO.saveNotification(eventType, args);
    }
}