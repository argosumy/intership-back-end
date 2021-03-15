package com.spd.baraholka.notification.services;
import com.spd.baraholka.notification.enumes.EventTypes;
import com.spd.baraholka.notification.model.Notification;
import com.spd.baraholka.notification.model.AdvertisementNotification;
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
public class CreateNotificationChangesAd implements CreateNotification {
    private final JavaMailSender emailSender;
    private final Configuration emailConfig;
    private final EventTypes types = EventTypes.CHANGES_ADVERTISEMENT;

    @Autowired
    public CreateNotificationChangesAd(JavaMailSender emailSender,
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
        AdvertisementNotification notificationAd = (AdvertisementNotification) notification;
        Map<String,String> model = new HashMap();
        model.put("reason" , notificationAd.getDescription());
        model.put("link_profile",notificationAd.getProfileLinkUser());
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Template template = emailConfig.getTemplate("wishlist-changes.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        mimeMessageHelper.setTo(notification.getSendTo());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(notification.getSubject());
        mimeMessageHelper.setFrom("Admin");

        return message;
    }
    //test method
//
//    public Notification getNotificationFromData(){
//        User user = new User();
//        user.setEmail("udizsumy@gmail.com");
//        user.setResourcesLink("#");
//
//        AdvertisementNotification notification = new AdvertisementNotification();
//        notification.setSendTo(user);
//        notification.setLinkAd("#");
//        notification.setSendTo(user);
//        notification.setEvent(EventTypes.CHANGES_ADVERTISEMENT.name());
//        notification.setSubject("Changes AD ");
//        notification.setDescription("changes.");
//
//        return notification;
//    }

}
