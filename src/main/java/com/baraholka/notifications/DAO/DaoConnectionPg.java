package com.baraholka.notifications.DAO;
import com.baraholka.notifications.enume.EventTypes;
import com.baraholka.notifications.enume.NotificationStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;

@Repository
public class DaoConnectionPg implements DAOConnection {
    private DaoConnectionPg daoConnectionPg;

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

    public DaoConnectionPg() {
        super();
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

    @Override
    public void saveNotification(EventTypes types, String description, int idUserOrIdAd){
        connect();
        if((types == EventTypes.NEW_ADVERTISEMENT) || (types == EventTypes.CHANGES_ADVERTISEMENT)) {
            try {
                if(types == EventTypes.NEW_ADVERTISEMENT) {
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(SQLQueries.GET_ALL_USER_ID);
                }
                else{
                    PreparedStatement preparedStatement = (PreparedStatement) statement;
                    preparedStatement = connection.prepareStatement(SQLQueries.GET_USER_ID_WISHLIST);
                    //table wish_list idAD == idUserOrIdAd
                    preparedStatement.setInt(1,idUserOrIdAd);
                    resultSet = preparedStatement.executeQuery();
                }
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    saveNotificationOne(types,"",id);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if((types == EventTypes.ACCOUNT_BAN) || (types == EventTypes.ADVERTISEMENT_BLOCK)){
            //table user  id <- idUserOrIdAd
            saveNotificationOne(types, description, idUserOrIdAd);
        }

        if(types == EventTypes.NEW_COMMENTS_ADVERTISEMENT){
            PreparedStatement preparedStatement = (PreparedStatement) statement;
            try {
                preparedStatement = connection.prepareStatement(SQLQueries.GET_TO_SEND_COMMENT_AD);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    saveNotificationOne(EventTypes.NEW_COMMENTS_ADVERTISEMENT,"",resultSet.getInt("to"));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }





        disconnect();
    }

    /*
     * @param description
     * @param recipient recipient == 0 (EventTypes.NEW_ADVERTISEMENT, EventTypes.CHANGES_ADVERTISEMENT)
     */
    private void saveNotificationOne(EventTypes types,String description, int recipient) {
        String status = NotificationStatus.NEW.name();
        String event = types.name();
        Date date = Date.valueOf(LocalDate.now());
        String sqlInsert = SQLQueries.SAVE_NOTIFICATION;
        PreparedStatement preparedStatement = (PreparedStatement) statement;

        try {
            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setInt(1,recipient);
            preparedStatement.setString(2,status);
            preparedStatement.setString(3,event);
            preparedStatement.setDate(4,date);
            preparedStatement.setString(5,description);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
