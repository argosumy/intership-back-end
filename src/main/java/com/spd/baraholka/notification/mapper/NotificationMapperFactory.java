package com.spd.baraholka.notification.mapper;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.advertisements.services.AdvertisementService;
import com.spd.baraholka.notification.dto.NotificationDto;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.AdvertisementNotification;
import com.spd.baraholka.notification.model.BanBlockNotification;
import com.spd.baraholka.notification.model.BaseNotification;
import com.spd.baraholka.notification.model.CommentNotification;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.service.UserService;

import java.time.LocalDateTime;

public class NotificationMapperFactory {

    private final UserService userService;
    private final AdvertisementService advertisementService;

    public NotificationMapperFactory(UserService userService, AdvertisementService advertisementService) {
        this.userService = userService;
        this.advertisementService = advertisementService;
    }

    public BaseNotification getNotification(NotificationDto notificationDto, EventType eventType) {

        UserDTO userMailTo = getUserById(notificationDto.getUserMailToId());
        UserDTO userById = getUserById(notificationDto.getUserId());
        Advertisement advertisementById = getAdvertisementById(notificationDto.getAdvertisementId());

        switch (eventType) {
            case ACCOUNT_BAN:
            case ADVERTISEMENT_BLOCK:
                BanBlockNotification banBlockNotification = new BanBlockNotification();
                setParameters(banBlockNotification, notificationDto, eventType, userMailTo);
                banBlockNotification.setReason(notificationDto.getReason());
                banBlockNotification.setEndDate(notificationDto.getBlockEndDate());
                return banBlockNotification;
            case NEW_ADVERTISEMENT:
            case ADVERTISEMENT_CHANGE:
                AdvertisementNotification advertisementNotification = new AdvertisementNotification();
                setParameters(advertisementNotification, notificationDto, eventType, userMailTo);
                advertisementNotification.setTitle(advertisementById.getTitle());
                advertisementNotification.setDescription(advertisementById.getDescription());
                return advertisementNotification;
            case NEW_ADVERTISEMENT_COMMENT:
            case NEW_COMMENT_ON_COMMENT:
                CommentNotification commentNotification = new CommentNotification();
                setParameters(commentNotification, notificationDto, eventType, userMailTo);
                commentNotification.setWriterName(userById.getFirstName() + " " + userById.getLastName());
                return commentNotification;
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
    }

    private void setParameters(BaseNotification notification, NotificationDto notificationDto, EventType eventType, UserDTO userMailTo) {
        notification.setSubject(eventType.name());
        notification.setMailTo(userMailTo.getEmail());
        notification.setObjectLink(notificationDto.getObjectLink());
        notification.setUserProfileLink(notificationDto.getUserProfileLink());
        notification.setEventType(eventType);
        notification.setCreationDate(LocalDateTime.now());
    }

    private UserDTO getUserById(int userId) {
        return userService.getUserById(userId);
    }

    private Advertisement getAdvertisementById(int advertisementId) {
        return advertisementService.findAdById(advertisementId).orElseThrow();
    }
}
