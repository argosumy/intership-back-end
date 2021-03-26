package com.spd.baraholka.notification.factory;

import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.AdvertisementNotification;
import com.spd.baraholka.notification.model.BanBlockNotification;
import com.spd.baraholka.notification.model.BaseNotification;
import com.spd.baraholka.notification.model.CommentNotification;

import java.util.HashMap;
import java.util.Map;

public class ModelFactory {

    public static final String PROFILE_LINK = "profileLink";

    private ModelFactory() {
    }

    public static Map<String, String> getModel(BaseNotification baseNotification) {
        Map<String, String> model = new HashMap<>();
        EventType eventType = baseNotification.getEventType();

        switch (eventType) {
            case NEW_ADVERTISEMENT_COMMENT:
            case NEW_COMMENT_ON_COMMENT:
                model.put("writer", ((CommentNotification) baseNotification).getWriterName());
                model.put("objectLink", baseNotification.getObjectLink());
                model.put(PROFILE_LINK, baseNotification.getUserProfileLink());
                break;

            case NEW_ADVERTISEMENT:
            case ADVERTISEMENT_CHANGE:
                model.put("title", ((AdvertisementNotification) baseNotification).getTitle());
                model.put("description", ((AdvertisementNotification) baseNotification).getDescription());
                model.put("mailTo", baseNotification.getMailTo());
                model.put("adLink", baseNotification.getObjectLink());
                model.put(PROFILE_LINK, baseNotification.getUserProfileLink());
                break;

            case ACCOUNT_BAN:
            case ADVERTISEMENT_BLOCK:
                model.put("blockEnd", ((BanBlockNotification) baseNotification).getEndDate().toString());
                model.put("reason", ((BanBlockNotification) baseNotification).getReason());
                model.put(PROFILE_LINK, baseNotification.getUserProfileLink());
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
        return model;
    }
}
