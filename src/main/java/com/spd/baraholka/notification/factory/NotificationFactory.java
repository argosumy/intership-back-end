package com.spd.baraholka.notification.factory;

import com.spd.baraholka.notification.dto.NotificationDto;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.BanBlockNotification;
import com.spd.baraholka.notification.model.BaseNotification;
import com.spd.baraholka.notification.model.CommentNotification;

public class NotificationFactory {

    private NotificationFactory() {
    }

    public static BaseNotification getNotification(NotificationDto notificationDto, EventType eventType) {
        switch (eventType) {
            case ACCOUNT_BAN:
            case ADVERTISEMENT_BLOCK:
//                final BanBlockNotification banBlockNotification = new BanBlockNotification();
//                banBlockNotification.setUserProfileLink(notificationDto.getUserProfileLink());
//                banBlockNotification.setAdName(notificationDto.getAdLink());
//                return banBlockNotification;
            case NEW_ADVERTISEMENT:
            case ADVERTISEMENT_CHANGE:
            case NEW_ADVERTISEMENT_COMMENT:
                final BanBlockNotification banBlockNotification = new BanBlockNotification();
                banBlockNotification.setUserProfileLink(notificationDto.getUserProfileLink());
                banBlockNotification.setAdName(notificationDto.getAdLink());
                return banBlockNotification;
            case NEW_COMMENT_ON_COMMENT:
            case NEW_MESSAGE_DIRECT:
                return new CommentNotification();
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
    }
}
