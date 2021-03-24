package com.spd.baraholka.notification.factory;

import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.BanBlockNotification;
import com.spd.baraholka.notification.model.BaseNotification;
import com.spd.baraholka.notification.model.CommentNotification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ModelFactory {

    private ModelFactory() {
    }

    public static Map<String, String> getModel(BaseNotification notification, EventType eventType) {
        Map<String, String> model = new HashMap<>();

        switch (eventType) {
            case NEW_ADVERTISEMENT_COMMENT:
                model.put("writer", ((CommentNotification) notification).getNameWriter());
                model.put("profileLink", notification.getUserProfileLink());
                model.put("adLink", ((CommentNotification) notification).getLinkAd());
                break;

            case ADVERTISEMENT_CHANGE:
                model.put("reason", notification.getDescription());
                model.put("profileLink", notification.getUserProfileLink());
                model.put("adLink", ((CommentNotification) notification).getLinkAd());
                break;

            case ACCOUNT_BAN:
                model.put("blockEnd", getBanDate((BanBlockNotification) notification));
                model.put("reason", notification.getDescription());
                model.put("profileLink", notification.getUserProfileLink());
                break;

            case ADVERTISEMENT_BLOCK:
                model.put("adName", notification.getNameAd());
                model.put("blockEnd", getBanDate((BanBlockNotification) notification));
                model.put("reason", notification.getDescription());
                model.put("profileLink", notification.getUserProfileLink());
                break;

            case NEW_ADVERTISEMENT:
                model.put("mainImage", ((CommentNotification) notification).getMainImage());
//                model.put("images", ((CommentNotification) notification).getImages());
                model.put("send_to_owner", ((CommentNotification) notification).getSendToOwner());
                model.put("title", "");
                model.put("description", notification.getDescription());
                model.put("profileLink", notification.getUserProfileLink());
                model.put("adLink", ((CommentNotification) notification).getLinkAd());
                break;
            case NEW_COMMENT_ON_COMMENT:
                model.put("writer", ((CommentNotification) notification).getNameWriter());
                model.put("profileLink", notification.getUserProfileLink());
                model.put("commentLink", ((CommentNotification) notification).getCommentLink());
                break;
            case NEW_MESSAGE_DIRECT:
                model.put("profileLink", notification.getUserProfileLink());
                model.put("commentLink", notification.getSubject());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
        return model;
    }

    private static String getBanDate(BanBlockNotification notification) {
        LocalDateTime localDateTime = notification.getDateBanNotification();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return localDateTime.format(formatter);
    }
}
