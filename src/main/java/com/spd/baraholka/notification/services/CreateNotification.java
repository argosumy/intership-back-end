package com.spd.baraholka.notification.services;

import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.BaseNotification;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public interface CreateNotification {
    MimeMessage createNotificationTemplate(BaseNotification notification) throws MessagingException, IOException, TemplateException;

    EventType getType();
}