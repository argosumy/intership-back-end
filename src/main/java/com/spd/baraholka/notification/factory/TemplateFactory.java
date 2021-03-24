package com.spd.baraholka.notification.factory;

import com.spd.baraholka.notification.enums.EventType;

public class TemplateFactory {

    private TemplateFactory() {
    }

    public static String template(EventType eventType) {
        switch (eventType) {
            case ACCOUNT_BAN:
                return "profile-block.ftl";
            case ADVERTISEMENT_BLOCK:
                return "ad-block.ftl";
            case NEW_ADVERTISEMENT:
                return "new-Ad.ftl";
            case ADVERTISEMENT_CHANGE:
                return "wishlist-changes.ftl";
            case NEW_ADVERTISEMENT_COMMENT:
            case NEW_COMMENT_ON_COMMENT:
                return "new-comment-ad.ftl";
            case NEW_MESSAGE_DIRECT:
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
    }
}
