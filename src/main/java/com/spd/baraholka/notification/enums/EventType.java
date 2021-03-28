package com.spd.baraholka.notification.enums;

public enum EventType {

    NEW_ADVERTISEMENT("advertisement-new.ftl"),
    ACCOUNT_BAN("account-ban.ftl"),
    ADVERTISEMENT_BLOCK("advertisement-block.ftl"),
    NEW_ADVERTISEMENT_COMMENT("new-advertisement-comment.ftl"),
    NEW_COMMENT_ON_COMMENT("new-comment-on_comment.ftl"),
    ADVERTISEMENT_CHANGE("wishlist-change.ftl");

    private final String templateLabel;

    EventType(String templateLabel) {
        this.templateLabel = templateLabel;
    }

    public String getTemplateLabel() {
        return templateLabel;
    }
}