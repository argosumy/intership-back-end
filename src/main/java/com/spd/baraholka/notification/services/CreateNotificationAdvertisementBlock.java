package com.spd.baraholka.notification.services;

import com.spd.baraholka.notification.enumes.EventTypes;
import com.spd.baraholka.notification.model.AdvertisementNotification;
import com.spd.baraholka.notification.model.Notification;
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
public class CreateNotificationAdvertisementBlock implements CreateNotification {
    private final JavaMailSender emailSender;
    private final Configuration emailConfig;
    private final EventTypes types = EventTypes.ADVERTISEMENT_BLOCK;
    @Autowired
    public CreateNotificationAdvertisementBlock(JavaMailSender emailSender, @Qualifier("freeMarker") Configuration emailConfig) {
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
    }

    @Override
    public EventTypes getType() {
        return types;
    }

    @Override
    public MimeMessage createNotificationTemplate(Notification notification) throws MessagingException, IOException, TemplateException {
        AdvertisementNotification notificationAd = (AdvertisementNotification) notification;
        LocalDateTime localDateTime = notification.getDate().plusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateBlock = localDateTime.format(formatter);
        Map<String,String> model = new HashMap();
        model.put("ad_name", notificationAd.getNameAd());
        model.put("block_ends" , dateBlock);
        model.put("reason", notification.getDescription());
        model.put("profilelink",notification.getProfileLinkUser());
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Template template = emailConfig.getTemplate("ad-block.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        mimeMessageHelper.setTo(notification.getSendTo());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject("Advertisement Block");
        mimeMessageHelper.setFrom("Admin");

        return message;
    }


    public EventTypes getTypes() {
        return types;
    }
}