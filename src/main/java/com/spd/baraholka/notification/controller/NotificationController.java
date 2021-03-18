package com.spd.baraholka.notification.controller;

import com.spd.baraholka.notification.enumes.EventTypes;
import com.spd.baraholka.notification.model.Notification;
import com.spd.baraholka.notification.services.factory.NotificationFactory;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@Controller
public class NotificationController {

private NotificationFactory factory;
private MimeMessage mimeMessage;
private Notification notification;

    @Autowired
    public NotificationController(NotificationFactory factory) {
        this.factory = factory;
    }

    public void notification(Map<String, String> args, EventTypes eventTypes) {
        creatTemplateNotification(eventTypes, notification);
    }

    private void creatTemplateNotification(EventTypes eventTypes, Notification notification) {
        try {
            mimeMessage = factory.buildNotification(eventTypes, notification);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}