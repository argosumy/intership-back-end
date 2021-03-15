package com.baraholka.notifications.DAO.factory;

import com.baraholka.notifications.enume.EventTypes;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SaveNotificationFactory {

    private List<SaveNotification> list;

    @Autowired
    public SaveNotificationFactory(List<SaveNotification> list) {
        this.list = list;
    }

    public SaveNotification buildSaveNotification(EventTypes types){
        SaveNotification saveNotification = null;
        for(SaveNotification object:list){
            if(object.getType() == types){
                saveNotification = object;
                break;
            }
        }
        return  saveNotification;
    }
}
