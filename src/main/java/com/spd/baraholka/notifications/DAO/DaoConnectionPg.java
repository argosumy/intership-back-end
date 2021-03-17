package com.spd.baraholka.notifications.DAO;
import com.spd.baraholka.notifications.DAO.factory.SaveNotification;
import com.spd.baraholka.notifications.DAO.factory.SaveNotificationFactory;
import com.spd.baraholka.notifications.enume.EventTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;

@Repository
public class DaoConnectionPg implements DAOConnection {
    private SaveNotificationFactory factory;

    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.name}")
    private String name;
    @Value("${datasource.password}")
    private String password;
    private Connection connection;


    @Autowired
    public DaoConnectionPg(SaveNotificationFactory factory) {
        super();
        this.factory = factory;
    }

    @Override
    public void connect(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);
            if (!connection.isClosed()) {
                System.out.println("Connected successful!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
        }
    }


    /*
      arg key("sendTo") - userId for AccountBAN, NewCommentToMyComment
      arg key("reason") - reason ban or block for AccountBan, AdvertisementBlock
      arg key("adId") - advertisement id for NotificationChangesAd, NewAd, AdvertisementBlock, NewCommentsAd
      arg key("writer") - NewCommentToMyComment
    */
    @Override
    public void saveNotification(EventTypes types, HashMap<String,String> arg){
        connect();
        try {
            SaveNotification saveNotification = factory.buildSaveNotification(types);
            saveNotification.save(arg,connection);
            connection.close();
        }catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
