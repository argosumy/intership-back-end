package com.spd.baraholka.notification.mapper;

import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.comments.dto.CommentUserInfoDto;
import com.spd.baraholka.image.persistance.entity.ImageResource;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.AdvertisementNotification;
import com.spd.baraholka.notification.model.BanBlockNotification;
import com.spd.baraholka.notification.model.BaseNotification;
import com.spd.baraholka.notification.model.CommentNotification;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotificationMapperFactory {

    public static final String HTTP_LOCALHOST_8080_API = "http://localhost:8080/api/";
    public static final String ADVERTISEMENT = "advertisement/";
    public static final String COMMENT = "comment/";
    public static final String USERS = "users/";

    public BaseNotification getNotification(Object... parameters) {
        var eventType = (EventType) parameters[0];
        var advertisement = (Advertisement) parameters[1];
        var userMailTo = (UserShortViewDTO) parameters[2];
        var comment = (CommentUserInfoDto) parameters[3];
        var images = (List<ImageResource>) parameters[4];
        var mainImage = (String) parameters[5];

        switch (eventType) {
            case ACCOUNT_BAN:
            case ADVERTISEMENT_BLOCK:
                BanBlockNotification banBlockNotification = new BanBlockNotification();
                setParameters(eventType, userEndpoint(userMailTo), banBlockNotification, advertisement, userMailTo, comment);
                banBlockNotification.setReason("SPAM DETECTED!");
                banBlockNotification.setEndDate(userMailTo.getEndDateOfBan());
                return banBlockNotification;
            case NEW_ADVERTISEMENT:
            case ADVERTISEMENT_CHANGE:
                AdvertisementNotification advertisementNotification = new AdvertisementNotification();
                setParameters(eventType, advertisementEndpoint(advertisement), advertisementNotification, advertisement, userMailTo, comment);
                advertisementNotification.setTitle(advertisement.getTitle());
                advertisementNotification.setDescription(advertisement.getDescription());
//                advertisementNotification.setMainImage(getMainImage(images));
                advertisementNotification.setMainImage(mainImage);
                return advertisementNotification;
            case NEW_ADVERTISEMENT_COMMENT:
            case NEW_COMMENT_ON_COMMENT:
                CommentNotification adCommentNotification = new CommentNotification();
                String endpoint = getEndpoint(eventType, advertisement, comment);
                setParameters(eventType, endpoint, adCommentNotification, advertisement, userMailTo, comment);
                adCommentNotification.setWriterName(comment.getUserName() + " " + comment.getUserLastName());
                return adCommentNotification;
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
    }

    private void setParameters(EventType eventType, String endpoint, Object... parameters) {
        BaseNotification notification = (BaseNotification) parameters[0];
        Advertisement advertisement = (Advertisement) parameters[1];
        UserShortViewDTO userMailTo = (UserShortViewDTO) parameters[2];
        CommentUserInfoDto comment = (CommentUserInfoDto) parameters[3];

        if (advertisement != null) {
            notification.setObjectLink(HTTP_LOCALHOST_8080_API + endpoint);
        }
        if (userMailTo != null) {
            notification.setMailTo(userMailTo.getEmail());
            notification.setUserProfileLink(HTTP_LOCALHOST_8080_API + userEndpoint(userMailTo));
        }
        notification.setSubject(eventType.name());
        notification.setEventType(eventType);
        notification.setCreationDate(LocalDateTime.now());
    }

    private String getEndpoint(EventType eventType, Advertisement advertisement, CommentUserInfoDto comment) {
        return eventType == EventType.NEW_ADVERTISEMENT_COMMENT ?
                advertisementEndpoint(advertisement) :
                commentEndpoint(comment);
    }

    private String advertisementEndpoint(Advertisement advertisement) {
        return ADVERTISEMENT + advertisement.getAdvertisementId();
    }

    private String userEndpoint(UserShortViewDTO user) {
        return USERS + user.getId();
    }

    private String commentEndpoint(CommentUserInfoDto comment) {
        return COMMENT + comment.getId();
    }
//
//    private String getMainImage(List<ImageResource> images) {
//        return images.stream()
//                .filter(ImageResource::getIsPrimary)
//                .map(ImageResource::getImageUrl)
//                .findFirst()
//                .orElse(null);
//    }
}
