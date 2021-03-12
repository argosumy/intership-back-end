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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class CreateNotificationAccountBan implements CreateNotification{
    private final JavaMailSender emailSender;
    private final Configuration emailConfig;
    private final EventTypes types = EventTypes.ACCOUNT_BAN;
    @Autowired
    public CreateNotificationAccountBan(JavaMailSender emailSender,
                                        @Qualifier("freeMarker")Configuration emailConfig){
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
    }

    @Override
    public EventTypes getType() {
        return types;
    }

    @Override
    public MimeMessage createNotificationTemplate(Notification notification) throws MessagingException, IOException, TemplateException {
        LocalDateTime localDateTime = notification.getDate().plusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateBan = localDateTime.format(formatter);
        Map<String,String> model = new HashMap();
        model.put("block_ends", dateBan);
        model.put("reason", notification.getDescription());
        model.put("profile_link", notification.getSendTo().getResourcesLink());
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Template template = emailConfig.getTemplate("profile-block.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        mimeMessageHelper.setTo(notification.getSendTo().getEmail());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(notification.getSubject());
        mimeMessageHelper.setFrom("Admin");
        return message;
    }

    //test method (DataBase)
    public Notification getNotificationFromData(){
        User userTo = new User();
        userTo.setEmail("udizsumy@gmail.com");
        userTo.setResourcesLink("#");
        Notification notification = new Notification();
        notification.setEvent(EventTypes.ACCOUNT_BAN.name());
        notification.setSubject("Account BAN");
        notification.setSendTo(userTo);
        notification.setDate(LocalDateTime.now());
        notification.setDescription("Your profile was blocked by moderator till");

        return notification;
    }


}
