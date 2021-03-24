package com.spd.baraholka.notification.services.factory;

import com.spd.baraholka.notification.services.CreateNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationFactory {
    private List<CreateNotification> list;

    @Autowired
    public NotificationFactory(List<CreateNotification> list) {
        this.list = list;
    }

//    public MimeMessage buildNotification(EventTypes type, BaseNotification notif) throws MessagingException, IOException, TemplateException {
//        Optional<CreateNotification> notification =  list.stream().filter(list -> type == list.getType()).findFirst();
//        return  notification.orElseThrow().createNotificationTemplate(notif);
//    }
}
