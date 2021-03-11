package com.spduniversity.notifications.services;


import com.spduniversity.notifications.enumes.EventTypes;
import com.spduniversity.notifications.model.Notification;
import com.spduniversity.notifications.model.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class CreateNotificationNewCommentsAd {
    private final JavaMailSender emailSender;
    private final Configuration emailConfig;


    @Autowired
    public CreateNotificationNewCommentsAd(JavaMailSender emailSender,
                                           @Qualifier("freeMarker") Configuration emailConfig) {
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
    }

    public MimeMessage createNotificationTemplate(Notification notification) throws MessagingException, IOException, TemplateException {
        Map<String,String> model = new HashMap();
        model.put("username", notification.getSendFrom().getFirst_name());
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Template template = emailConfig.getTemplate("new-comment.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        mimeMessageHelper.setTo(notification.getSendTo().getEmail());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject("New comments");
        mimeMessageHelper.setFrom("Admin");

        return message;
    }

    //test method
    public Notification getNotificationFromData(){
        User userTo = new User();
        userTo.setEmail("udizsumy@gmail.com");
        User userFrom = new User();
        userFrom.setFirst_name("Ludmila");

        Notification notification = new Notification();
        notification.setEvent(EventTypes.NEW_COMMENTS_ADVERTISEMENT.name());
        notification.setSubject("New comments Ad");
        notification.setSendTo(userTo);
        notification.setSendFrom(userFrom);
        notification.setDescription("New comments Ad");

        return notification;
    }


}