package com.spd.baraholka.notification.services;

import com.spd.baraholka.notification.enumes.EventTypes;
import com.spd.baraholka.notification.model.Notification;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public interface CreateNotification {

    public MimeMessage createNotificationTemplate(Notification notification) throws MessagingException, IOException, TemplateException;
    public EventTypes getType();
}
