package com.spd.baraholka.notification.controller;

import com.spd.baraholka.notification.dto.NotificationDto;
import com.spd.baraholka.notification.mapper.NotificationDaoMapper;
import com.spd.baraholka.notification.mapper.NotificationMapperFactory;
import com.spd.baraholka.notification.service.NotificationService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationMapperFactory notificationMapperFactory;
    private final NotificationDaoMapper notificationDaoMapper;

    @Autowired
    public NotificationController(NotificationService notificationService,
                                  NotificationMapperFactory notificationMapperFactory,
                                  NotificationDaoMapper notificationDaoMapper) {
        this.notificationService = notificationService;
        this.notificationMapperFactory = notificationMapperFactory;
        this.notificationDaoMapper = notificationDaoMapper;
    }

    @PostMapping
    public int sendNotification(@RequestBody NotificationDto notificationDto) throws MessagingException, IOException, TemplateException {
        int savedNotificationId = notificationService.saveNotification(notificationDaoMapper.toNotification(notificationDto));
        notificationService.sendMessage(notificationMapperFactory.getNotification(notificationDto));
        return savedNotificationId;
    }
}