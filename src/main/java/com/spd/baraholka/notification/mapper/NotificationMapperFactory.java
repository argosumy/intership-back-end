package com.spd.baraholka.notification.mapper;

import com.spd.baraholka.advertisement.controller.dto.FullAdvertisementDTO;
import com.spd.baraholka.comments.dto.CommentUserInfoDto;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.AdvertisementNotification;
import com.spd.baraholka.notification.model.BanBlockNotification;
import com.spd.baraholka.notification.model.BaseNotification;
import com.spd.baraholka.notification.model.CommentNotification;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotificationMapperFactory {

    public static final String HTTP_LOCALHOST_8080_API = "http://localhost:8080/api/";
    public static final String ADVERTISEMENT = "advertisement/";
    public static final String COMMENT = "comment/";
    public static final String USERS = "users/";
    public static final int ZERO = 0;
    public static final int TWO = 2;
    public static final int ONE = 1;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;

    public BaseNotification getNotification(Object... parameters) {
        var eventType = (EventType) parameters[ZERO];
        var advertisement = (FullAdvertisementDTO) parameters[ONE];
        var user = (UserShortViewDTO) parameters[TWO];
        var comment = (CommentUserInfoDto) parameters[THREE];
        var images = (List<String>) parameters[FOUR];
        var mainImage = (String) parameters[FIVE];

        switch (eventType) {
            case ACCOUNT_BAN:
            case ADVERTISEMENT_BLOCK:
                BanBlockNotification banBlockNotification = new BanBlockNotification();
                setParameters(eventType, userEndpoint(user), banBlockNotification, user);
                banBlockNotification.setReason("SPAM DETECTED!"); //Mock
                banBlockNotification.setEndDate(user.getEndDateOfBan());
                return banBlockNotification;
            case NEW_ADVERTISEMENT:
            case ADVERTISEMENT_CHANGE:
                AdvertisementNotification advertisementNotification = new AdvertisementNotification();
                setParameters(eventType, advertisementEndpoint(advertisement), advertisementNotification, user);
                advertisementNotification.setTitle(advertisement.getTitle());
                advertisementNotification.setDescription(advertisement.getDescription());
                advertisementNotification.setImages(images);
                advertisementNotification.setMainImage(mainImage);
                return advertisementNotification;
            case NEW_ADVERTISEMENT_COMMENT:
            case NEW_COMMENT_ON_COMMENT:
                CommentNotification adCommentNotification = new CommentNotification();
                String endpoint = getEndpoint(eventType, advertisement, comment);
                setParameters(eventType, endpoint, adCommentNotification, user);
                adCommentNotification.setWriterName(comment.getUserName() + " " + comment.getUserLastName());
                return adCommentNotification;
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
    }

    private void setParameters(EventType eventType, String endpoint, BaseNotification notification, UserShortViewDTO user) {
        notification.setObjectLink(HTTP_LOCALHOST_8080_API + endpoint);
        notification.setMailTo(user.getEmail());
        notification.setUserProfileLink(HTTP_LOCALHOST_8080_API + userEndpoint(user));
        notification.setSubject(eventType.name());
        notification.setEventType(eventType);
        notification.setCreationDate(LocalDateTime.now());
    }

    private String getEndpoint(EventType eventType, FullAdvertisementDTO advertisement, CommentUserInfoDto comment) {
        return eventType == EventType.NEW_ADVERTISEMENT_COMMENT ?
                advertisementEndpoint(advertisement) :
                commentEndpoint(comment);
    }

    private String advertisementEndpoint(FullAdvertisementDTO advertisement) {
        return ADVERTISEMENT + advertisement.getAdvertisementId();
    }

    private String userEndpoint(UserShortViewDTO user) {
        return USERS + user.getId();
    }

    private String commentEndpoint(CommentUserInfoDto comment) {
        return COMMENT + comment.getId();
    }
}
