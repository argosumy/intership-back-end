package com.baraholka.notifications.DAO;
import com.baraholka.notifications.DAO.factory.SaveNotification;
import com.baraholka.notifications.DAO.factory.SaveNotificationFactory;
import com.baraholka.notifications.enume.EventTypes;
import com.baraholka.notifications.enume.NotificationStatus;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

@Repository
public class DaoConnectionPg implements DAOConnection {
    private DaoConnectionPg daoConnectionPg;
    private SaveNotificationFactory factory;

    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.name}")
    private String name;
    @Value("${datasource.password}")
    private String password;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Driver driver;


    @Autowired
    public DaoConnectionPg(SaveNotificationFactory factory) {
        super();
        this.factory = factory;
    }

    @Override
    public void connect(){
        try {
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            connection = DriverManager.getConnection(url, name, password);
            if (!connection.isClosed()) {
                System.out.println("Connected successful!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void disconnect() {
        try{
            connection.close();
            resultSet.close();
            statement.close();
            System.out.println("Connection was closed");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*if(EventTypes.NEW_ADVERTISEMENT or EventTypes.CHANGES_ADVERTISEMENT or NEW_COMMENTS_ADVERTISEMENT)
    *           then int idUserOrIdAd = IdAd
    * if(EventTypes.ACCOUNT_BAN or EventTypes.ADVERTISEMENT_BLOCK) then idUserOrIdAd = idUser
    *
    * */
    @Override
    public void saveNotification(EventTypes types, HashMap<String,String> arg){
        connect();
        try {
            SaveNotification saveNotification = factory.buildSaveNotification(types);
            saveNotification.save(arg,connection);
            connection.close();
        }catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    /*
     * @param description
     * @param recipient recipient == 0 (EventTypes.NEW_ADVERTISEMENT, EventTypes.CHANGES_ADVERTISEMENT)
     */
    private void saveNotificationOne(EventTypes types,String description, int recipient) {
        }
}
