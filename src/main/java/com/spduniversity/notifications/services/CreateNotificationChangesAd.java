package com.spduniversity.notifications.services;
import com.spduniversity.notifications.enumes.EventTypes;
import com.spduniversity.notifications.model.Notification;
import com.spduniversity.notifications.model.NotificationAdvertisement;
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
        NotificationAdvertisement notificationAd = (NotificationAdvertisement) notification;
        Map<String,String> model = new HashMap();
        model.put("reason" , notificationAd.getDescription());
        model.put("link_profile",notificationAd.getSendTo().getResourcesLink());
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Template template = emailConfig.getTemplate("wishlist-changes.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        mimeMessageHelper.setTo(notification.getSendTo().getEmail());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(notification.getSubject());
        mimeMessageHelper.setFrom("Admin");

        return message;
    }
    //test method
    @Override
    public Notification getNotificationFromData(){
        User user = new User();
        user.setEmail("udizsumy@gmail.com");
        user.setResourcesLink("#");

        NotificationAdvertisement notification = new NotificationAdvertisement();
        notification.setSendTo(user);
        notification.setLinkAd("#");
        notification.setSendTo(user);
        notification.setEvent(EventTypes.CHANGES_ADVERTISEMENT.name());
        notification.setSubject("Changes AD ");
        notification.setDescription("changes.");

        return notification;
    }

}
