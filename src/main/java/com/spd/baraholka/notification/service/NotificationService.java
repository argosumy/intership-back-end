package com.spd.baraholka.notification.service;

import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.repository.NotificationRepository;
import com.spd.baraholka.notification.repository.NotificationRepositoryImpl;

public class NotificationService {

    private final NotificationRepositoryImpl notificationRepositoryImpl;

    public NotificationService(NotificationRepositoryImpl notificationRepositoryImpl) {
        this.notificationRepositoryImpl = notificationRepositoryImpl;
    }

    public NotificationRepository getNotification(EventType eventType) {

        switch (eventType) {
            case NEW_ADVERTISEMENT:
                return new FourPdaArticleStrategy();
            case REPLY_UA:
                return new ReplyUaArticleStrategy();
            case SEGODNYA_UA:
                return new SegodnyaUaArticleStrategy();
            default:
                throw new IllegalStateException("Unexpected value: " + value);
        }
    }

    NEW_ADVERTISEMENT,
    ACCOUNT_BAN,
    ADVERTISEMENT_BLOCK,
    NEW_COMMENTS_ADVERTISEMENT,
    NEW_MESSAGE_DIRECT,
    NEW_COMMENTS_TO_MY_COMMENTS,
    CHANGES_ADVERTISEMENT
}
