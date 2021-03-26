package com.spd.baraholka.notification.factory;

import com.spd.baraholka.notification.enums.EventType;

public class TemplateFactory {

    private TemplateFactory() {
    }

    public static String template(EventType eventType) {
        switch (eventType) {
            case ACCOUNT_BAN:
                return "account-ban.ftl";
            case ADVERTISEMENT_BLOCK:
                return "advertisement-block.ftl";
            case NEW_ADVERTISEMENT:
                return "advertisement-new.ftl";
            case ADVERTISEMENT_CHANGE:
                return "wishlist-change.ftl";
            case NEW_ADVERTISEMENT_COMMENT:
                return "new-advertisement-comment.ftl";
            case NEW_COMMENT_ON_COMMENT:
                return "new-comment-on_comment.ftl";
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
    }
}
