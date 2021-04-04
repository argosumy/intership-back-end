package com.spd.baraholka.notification.factory;

import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.AdvertisementNotification;
import com.spd.baraholka.notification.model.BanBlockNotification;
import com.spd.baraholka.notification.model.BaseNotification;
import com.spd.baraholka.notification.model.CommentNotification;

import java.util.HashMap;
import java.util.Map;

public final class ModelFactory {

    public static final String PROFILE_LINK = "profileLink";
    public static final String OBJECT_LINK = "objectLink";

    private ModelFactory() {
    }

    public static Map<String, Object> getModel(BaseNotification baseNotification) {
        Map<String, Object> model = new HashMap<>();
        EventType eventType = baseNotification.getEventType();
        setLinks(baseNotification, model);

        switch (eventType) {
            case NEW_ADVERTISEMENT_COMMENT:
            case NEW_COMMENT_ON_COMMENT:
                model.put("writer", ((CommentNotification) baseNotification).getWriterName());
                break;
            case NEW_ADVERTISEMENT:
            case ADVERTISEMENT_CHANGE:
                model.put("title", ((AdvertisementNotification) baseNotification).getTitle());
                model.put("description", ((AdvertisementNotification) baseNotification).getDescription());
                model.put("mailTo", baseNotification.getMailTo());
                model.put("mainImage", ((AdvertisementNotification) baseNotification).getMainImage());
//                ((AdvertisementNotification) baseNotification).getImages()
//                        .forEach(image -> model.put("image", image));
                model.put("images", ((AdvertisementNotification) baseNotification).getImages());
                break;
            case ACCOUNT_BAN:
            case ADVERTISEMENT_BLOCK:
                model.put("blockEnd", ((BanBlockNotification) baseNotification).getEndDate().toString());
                model.put("reason", ((BanBlockNotification) baseNotification).getReason());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
        return model;
    }

    private static void setLinks(BaseNotification baseNotification, Map<String, Object> model) {
        model.put(OBJECT_LINK, baseNotification.getObjectLink());
        model.put(PROFILE_LINK, baseNotification.getUserProfileLink());
    }
}
