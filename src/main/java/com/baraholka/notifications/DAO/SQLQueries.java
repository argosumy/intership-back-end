package com.baraholka.notifications.DAO;

public class SQLQueries {
    public final static String SAVE_NOTIFICATION = "INSERT INTO NOTIFICATIONS (send_to,status,event,date,description) "
                                                             + "VAlUES (?,?,?,?,?)";
    public final static String GET_ALL_USER_ID = "SELECT ID FROM USERS";
    public final static String GET_USER_ID_WISHLIST = "SELECT wish_list.user_id FROM wish_list INNER JOIN advertisement AS ad "
                                                                             + "ON ad.id = wish_list.ad_id WHERE ad.id = ?";
    public final static String GET_TO_SEND_COMMENT_AD ="SELECT ad.user_id as to FROM comments INNER JOIN advertisement AS ad ON comments.advertisement_id = ad.id";




}