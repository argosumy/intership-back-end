package com.spd.baraholka.notifications.DAO;
public class SQLQueries {
    public final static String SAVE_NOTIFICATION_BAN = "INSERT INTO NOTIFICATIONS (send_to,status,event,date,description) "
            + "VAlUES (?,?,?,?,?)";
    public final static String SAVE_NOTIFICATION_BLOCK_AD = "INSERT INTO NOTIFICATIONS (send_to,status,event,date,description,ad_id) "
            + "VAlUES (?,?,?,?,?,?)";
    public final static String SAVE_NOTIFICATION_NEW_AD = "INSERT INTO NOTIFICATIONS (send_to,status,event,date,ad_id) "
            + "VAlUES (?,?,?,?,?)";
    public  final static String SAVE_NOTIFICATION_NEW_COMMENT_TO_MY_COMMENT = "INSERT INTO "
            + "NOTIFICATIONS (send_to,send_from,status,event,date) VALUES (?,?,?,?,?)";
    public final static String GET_ALL_USER_ID = "SELECT ID FROM USERS";
    public final static String GET_USER_ID_WISHLIST = "SELECT wish_list.user_id FROM wish_list INNER JOIN advertisement AS ad "
                                                                             + "ON ad.id = wish_list.ad_id WHERE ad.id = ?";
    public final static String GET_TO_SEND_COMMENT_AD = "SELECT user_id FROM advertisement WHERE advertisement.id = ?";
    public final static String GET_TO_SEND_BLOCK_AD = "SELECT user_id FROM advertisement WHERE advertisement.id = ?";
}