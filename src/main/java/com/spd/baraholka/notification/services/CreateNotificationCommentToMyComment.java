package com.spd.baraholka.notification.services;

import com.spd.baraholka.notification.enumes.EventTypes;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CreateNotificationCommentToMyComment extends CreateNotificationNewCommentsAd {


    @Autowired
    public CreateNotificationCommentToMyComment(JavaMailSender emailSender, @Qualifier("freeMarker") Configuration emailConfig) {
        super(emailSender, emailConfig);
    }

    @Override
    public EventTypes getType() {
        return EventTypes.NEW_COMMENTS_TO_MY_COMMENTS;
    }
}
