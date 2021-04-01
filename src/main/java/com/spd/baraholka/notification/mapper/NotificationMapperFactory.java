package com.spd.baraholka.notification.mapper;

import com.spd.baraholka.advertisement.controller.dto.FullAdvertisementDTO;
import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.notification.dto.NotificationDto;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.AdvertisementNotification;
import com.spd.baraholka.notification.model.BanBlockNotification;
import com.spd.baraholka.notification.model.BaseNotification;
import com.spd.baraholka.notification.model.CommentNotification;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapperFactory {

    private final UserService userService;
    private final AdvertisementService advertisementService;

    public NotificationMapperFactory(UserService userService, AdvertisementService advertisementService) {
        this.userService = userService;
        this.advertisementService = advertisementService;
    }

    public BaseNotification getNotification(NotificationDto notificationDto) {
        UserDTO userById;
        FullAdvertisementDTO advertisementById;
        UserDTO userMailTo = getUserById(notificationDto.getUserMailToId());
        EventType eventType = notificationDto.getEventType();

        switch (eventType) {
            case ACCOUNT_BAN:
            case ADVERTISEMENT_BLOCK:
                BanBlockNotification banBlockNotification = new BanBlockNotification();
                setParameters(banBlockNotification, notificationDto, userMailTo);
                banBlockNotification.setReason(notificationDto.getReason());
                banBlockNotification.setEndDate(notificationDto.getBlockEndDate());
                return banBlockNotification;
            case NEW_ADVERTISEMENT:
            case ADVERTISEMENT_CHANGE:
                AdvertisementNotification advertisementNotification = new AdvertisementNotification();
                advertisementById = getAdvertisementById(notificationDto.getAdvertisementId());
                setParameters(advertisementNotification, notificationDto, userMailTo);
                advertisementNotification.setTitle(advertisementById.getTitle());
                advertisementNotification.setDescription(advertisementById.getDescription());
                return advertisementNotification;
            case NEW_ADVERTISEMENT_COMMENT:
            case NEW_COMMENT_ON_COMMENT:
                CommentNotification commentNotification = new CommentNotification();
                userById = getUserById(notificationDto.getUserId());
                setParameters(commentNotification, notificationDto, userMailTo);
                commentNotification.setWriterName(userById.getFirstName() + " " + userById.getLastName());
                return commentNotification;
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
    }

    private void setParameters(BaseNotification notification, NotificationDto notificationDto, UserDTO userMailTo) {
        notification.setSubject(notificationDto.getEventType().name());
        notification.setMailTo(userMailTo.getEmail());
        notification.setObjectLink(notificationDto.getObjectLink());
        notification.setUserProfileLink(notificationDto.getUserProfileLink());
        notification.setEventType(notificationDto.getEventType());
        notification.setCreationDate(LocalDateTime.now());
    }

    private UserDTO getUserById(int userId) {
        return userService.getUserById(userId);
    }

    private FullAdvertisementDTO getAdvertisementById(int advertisementId) {
        return advertisementService.getAdvertisementById(advertisementId);
    }
}
