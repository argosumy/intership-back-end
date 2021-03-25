package com.spd.baraholka.notification.factory;

import com.spd.baraholka.notification.dto.NotificationDto;
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

    public static Map<String, String> getModel(NotificationDto notification, EventType eventType) {
        Map<String, String> model = new HashMap<>();

        switch (eventType) {
            case NEW_ADVERTISEMENT_COMMENT:
//                model.put("writer", ((CommentNotification) notification).getWriterName());
                model.put("profileLink", notification.getUserProfileLink());
//                model.put("adLink", ((CommentNotification) notification).getAdLink());
                model.put("adLink", notification.getAdLink());
                break;

            case ADVERTISEMENT_CHANGE:
                model.put("reason", notification.getDescription());
                model.put("profileLink", notification.getUserProfileLink());
                model.put("adLink", ((CommentNotification) notification).getAdLink());
                break;

            case ACCOUNT_BAN:
            case ADVERTISEMENT_BLOCK:
                model.put("blockEnd", getBanDate((BanBlockNotification) notification));
                model.put("reason", notification.getDescription());
                model.put("profileLink", notification.getUserProfileLink());
                break;

            case ADVERTISEMENT_BLOCK:
                model.put("adName", notification.getAdName());
                model.put("blockEnd", getBanDate((BanBlockNotification) notification));
                model.put("reason", notification.getDescription());
                model.put("profileLink", notification.getUserProfileLink());
                break;

            case NEW_ADVERTISEMENT:
                model.put("mainImage", ((CommentNotification) notification).getMainImage());
//                model.put("images", ((CommentNotification) notification).getImages());
                model.put("sendTo", ((CommentNotification) notification).getOwnerName());
                model.put("title", "");
                model.put("description", notification.getDescription());
                model.put("profileLink", notification.getUserProfileLink());
                model.put("adLink", ((CommentNotification) notification).getAdLink());
                break;
            case NEW_COMMENT_ON_COMMENT:
                model.put("writer", ((CommentNotification) notification).getWriterName());
                model.put("profileLink", notification.getUserProfileLink());
                model.put("commentLink", ((CommentNotification) notification).getCommentLink());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
        return model;
    }

    private static String getBanDate(BanBlockNotification notification) {
        LocalDateTime localDateTime = notification.getBanDateNotification();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return localDateTime.format(formatter);
    }
}
