package com.spd.baraholka.notifications.DAO.factory;

import com.spd.baraholka.notifications.enume.EventTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SaveNotificationFactory {

    private List<SaveNotification> list;

    @Autowired
    public SaveNotificationFactory(List<SaveNotification> list) {
        this.list = list;
    }

    public SaveNotification buildSaveNotification(EventTypes types) {
        SaveNotification saveNotification = null;
        for (SaveNotification object:list) {
            if (object.getType() == types) {
                saveNotification = object;
                break;
            }
        }
        return  saveNotification;
    }
}
