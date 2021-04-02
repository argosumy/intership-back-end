package com.spd.baraholka.notification.mapper;

import com.spd.baraholka.advertisement.controller.dto.FullAdvertisementDTO;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.service.AdvertisementService;
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

    public static final String HTTP_LOCALHOST_8080_API = "http://localhost:8080/api/";
    private final UserService userService;
    private final AdvertisementService advertisementService;

    public NotificationMapperFactory(UserService userService, AdvertisementService advertisementService) {
        this.userService = userService;
        this.advertisementService = advertisementService;
    }

//    public BaseNotification getNotification(NotificationDto notificationDto) {
//        UserDTO userById;
//        FullAdvertisementDTO advertisementById;
//        UserDTO userMailTo = getUserById(notificationDto.getUserMailToId());
//        EventType eventType = notificationDto.getEventType();
//
//        switch (eventType) {
//            case ACCOUNT_BAN:
//            case ADVERTISEMENT_BLOCK:
//                BanBlockNotification banBlockNotification = new BanBlockNotification();
//                setParameters(banBlockNotification, notificationDto, userMailTo);
//                banBlockNotification.setReason(notificationDto.getReason());
//                banBlockNotification.setEndDate(notificationDto.getBlockEndDate());
//                return banBlockNotification;
//            case NEW_ADVERTISEMENT:
//            case ADVERTISEMENT_CHANGE:
//                AdvertisementNotification advertisementNotification = new AdvertisementNotification();
//                advertisementById = getAdvertisementById(notificationDto.getAdvertisementId());
//                setParameters(advertisementNotification, notificationDto, userMailTo);
//                advertisementNotification.setTitle(advertisementById.getTitle());
//                advertisementNotification.setDescription(advertisementById.getDescription());
//                return advertisementNotification;
//            case NEW_ADVERTISEMENT_COMMENT:
//            case NEW_COMMENT_ON_COMMENT:
//                CommentNotification commentNotification = new CommentNotification();
//                userById = getUserById(notificationDto.getUserId());
//                setParameters(commentNotification, notificationDto, userMailTo);
//                commentNotification.setWriterName(userById.getFirstName() + " " + userById.getLastName());
//                return commentNotification;
//            default:
//                throw new IllegalStateException("Unexpected value: " + eventType);
//        }
//    }
//
//    private void setParameters(BaseNotification notification, NotificationDto notificationDto, UserDTO userMailTo) {
//        notification.setSubject(notificationDto.getEventType().name());
//        notification.setMailTo(userMailTo.getEmail());
//        notification.setObjectLink(notificationDto.getObjectLink());
//        notification.setUserProfileLink(notificationDto.getUserProfileLink());
//        notification.setEventType(notificationDto.getEventType());
//        notification.setCreationDate(LocalDateTime.now());
//    }
//
//    private UserDTO getUserById(int userId) {
//        return userService.getUserById(userId);
//    }
//
//    private FullAdvertisementDTO getAdvertisementById(int advertisementId) {
//        return advertisementService.getAdvertisementById(advertisementId);
//    }
//}

    public BaseNotification getNotification(Object... parameters) {
//        UserDTO userById;
//        FullAdvertisementDTO advertisementById;
//        UserDTO userMailTo;

        EventType eventType = (EventType) parameters[0];
        Advertisement advertisement = (Advertisement) parameters[1];
        UserDTO userMailTo = (UserDTO) parameters[2];
        Comment comment = parameters[3];

        switch (eventType) {
            case ACCOUNT_BAN:
            case ADVERTISEMENT_BLOCK:
                BanBlockNotification banBlockNotification = new BanBlockNotification();

                setParameters(banBlockNotification, advertisement, userMailTo);
                banBlockNotification.setReason("SPAM DETECTED!");
                banBlockNotification.setEndDate(userMailTo.getEndDateOfBan());
                return banBlockNotification;
            case NEW_ADVERTISEMENT:
            case ADVERTISEMENT_CHANGE:
                AdvertisementNotification advertisementNotification = new AdvertisementNotification();

                setParameters(advertisementNotification, advertisement, userMailTo);

                advertisementNotification.setTitle(advertisement.getTitle());
                advertisementNotification.setDescription(advertisement.getDescription());
                return advertisementNotification;
            case NEW_ADVERTISEMENT_COMMENT:
            case NEW_COMMENT_ON_COMMENT:
                CommentNotification commentNotification = new CommentNotification();

                setParameters(commentNotification, advertisement, userMailTo);
                commentNotification.setWriterName(commentDto.getFirstName() + " " + commentDto.getLastName());
                return commentNotification;
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
    }

    //    private void setParameters(BaseNotification notification, Advertisement advertisement, UserDTO userMailTo) {
    private void setParameters(Object... parameters) {
        BaseNotification notification = (BaseNotification) parameters[0];
        Advertisement advertisement = (Advertisement) parameters[1];
        UserDTO userMailTo = (UserDTO) parameters[2];
        Comment comment = parameters[3];

        if (advertisement != null) {
            notification.setObjectLink(HTTP_LOCALHOST_8080_API + "advertisement/" + advertisement.getAdvertisementId());
        }
        if (userMailTo != null) {
            notification.setMailTo(userMailTo.getEmail());
            notification.setUserProfileLink(HTTP_LOCALHOST_8080_API + "users/" + userMailTo.getId());
        }
        if (comment != null) {

        }
        notification.setSubject(EventType.NEW_ADVERTISEMENT.name());
        notification.setEventType(EventType.NEW_ADVERTISEMENT);
        notification.setCreationDate(LocalDateTime.now());
//        notification.setObjectLink(HTTP_LOCALHOST_8080_API + "advertisement/" + advertisement.getAdvertisementId());
    }

//    private void setLinks(BaseNotification notification, int userId, int adId) {

}

    private UserDTO getUserById(int userId) {
        return userService.getUserById(userId);
    }

    private FullAdvertisementDTO getAdvertisementById(int advertisementId) {
        return advertisementService.getAdvertisementById(advertisementId);
    }
}
