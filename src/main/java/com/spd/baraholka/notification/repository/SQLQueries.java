package com.spd.baraholka.notification.repository;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class SQLQueries {
    public static final String GET_ID_EVENT_BY_NAME = "SELECT id FROM events WHERE name = ?";
    public static final String GET_ID_STATUS_BY_NAME = "SELECT id FROM statuses WHERE name = ?";
    public static final String SAVE_NOTIFICATION_BAN = "INSERT INTO NOTIFICATIONS (recipient, status, event, date, description) VAlUES(?,?,?,?,?)";
    public static final String SAVE_NOTIFICATION_AD = "INSERT INTO NOTIFICATIONS (recipient,status,event,date,description,ad_id) VAlUES(?,?,?,?,?,?)";
    public static final String SAVE_NOTIFICATION_CHANGES_AD = "INSERT INTO NOTIFICATIONS (recipient,status,event,date,description,ad_id) VAlUES(?,?,?,?,?,?)";
    public static final String SAVE_NOTIFICATION_NEW_AD = "INSERT INTO NOTIFICATIONS (recipient,status,event,date,ad_id) VAlUES (?,?,?,?,?)";
    public static final String SAVE_NOTIFICATION_NEW_COMMENT_TO_MY_COMMENT = "INSERT INTO NOTIFICATIONS (recipient,writer,status,event,date) VALUES (?,?,?,?,?)";
    public static final String SAVE_NOTIFICATION_COMMENT_TO_AD = "INSERT INTO NOTIFICATIONS (recipient, writer, status,event,date,ad_id) VAlUES(?,?,?,?,?,?)";
    public static final String GET_ALL_USER_ID = "SELECT ID FROM USERS";
    public static final String GET_USER_ID_WISHLIST = "SELECT wish_list.user_id FROM wish_list INNER JOIN advertisement AS ad ON ad.id = wish_list.ad_id WHERE ad.id = ?";
    public static final String GET_USER_ID_TO_SEND_ACTION_AD = "SELECT user_id FROM advertisement WHERE advertisement.id = ?";
    public static final String GET_USER_ID_BY_COMMENT = "SELECT user_id FROM comments WHERE id = ?";
    public static final String GET_USER_ID_BY_TERMS_SUBSCRIPTION = "SELECT user_id FROM subscribers " +
            "INNER JOIN terms_subscription AS terms ON subscribers.subscription_ad = terms.id WHERE terms.name = ?";
}