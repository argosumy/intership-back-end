package com.spd.baraholka.notification.controller;

import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.notification.dto.NotificationDto;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.mapper.NotificationMapper;
import com.spd.baraholka.notification.mapper.NotificationMapperFactory;
import com.spd.baraholka.notification.service.NotificationService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;

@RestController("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationMapperFactory notificationMapperFactory;
    private final NotificationMapper notificationMapper;
    private final AdvertisementService advertisementService;

    @Autowired
    public NotificationController(NotificationService notificationService,
                                  NotificationMapperFactory notificationMapperFactory,
                                  NotificationMapper notificationMapper, AdvertisementService advertisementService) {
        this.notificationService = notificationService;
        this.notificationMapperFactory = notificationMapperFactory;
        this.notificationMapper = notificationMapper;
        this.advertisementService = advertisementService;
    }

    @PostMapping
    public int sendNotification(@RequestBody  NotificationDto notificationDto) throws MessagingException, IOException, TemplateException {
        int savedNotificationId = notificationService.saveNotification(notificationMapper.toNotification(notificationDto));
//        if (notificationDto.getEventType() == EventType.NEW_ADVERTISEMENT) {
//            notificationService.sendAllUsersNotification(notificationMapperFactory.getNotification(notificationDto));
//        }
        notificationService.sendMessage(notificationMapperFactory.getNotification(notificationDto));
        return savedNotificationId;
    }
}