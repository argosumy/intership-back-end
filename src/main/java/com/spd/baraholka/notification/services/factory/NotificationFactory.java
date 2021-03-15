package com.spd.baraholka.notification.services.factory;

import com.spd.baraholka.notification.enumes.EventTypes;
import com.spd.baraholka.notification.model.Notification;
import com.spd.baraholka.notification.services.CreateNotification;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationFactory {
    private List<CreateNotification> list;

    @Autowired
    public NotificationFactory(List<CreateNotification> list) {
        this.list = list;
    }

    public MimeMessage buildNotification(EventTypes type) throws MessagingException, IOException, TemplateException {
        Optional<CreateNotification> notification =  list.stream().filter(list -> type == list.getType()).findFirst();
        //bild model notification
       // notification.orElseThrow().createNotificationTemplate();
        return null;
    }
}
