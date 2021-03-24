package com.spd.baraholka.notification.controller;

import com.spd.baraholka.notification.dto.NotificationDto;
import com.spd.baraholka.notification.repository.NotificationDAO;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.service.NotificationService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

@RestController("/notifications")
public class NotificationController {
    private final NotificationDAO notificationDAO;
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationDAO notificationDAO, NotificationService notificationService) {
        this.notificationDAO = notificationDAO;
        this.notificationService = notificationService;
    }

//    public void notification(Map<String, String> args, EventType eventType) {
//        saveNotification(args, eventType);
//    }

//    @PostMapping
//    public void saveNotification(Map<String, String> args, EventType eventType) {
//        notificationDAO.saveNotification(eventType, args);
//    }

    @PostMapping
    public void sendNotification(@RequestBody NotificationDto notificationDto) throws MessagingException, IOException, TemplateException {
        notificationService.sendMessage(notificationDto, EventType.NEW_ADVERTISEMENT_COMMENT);
    }
}