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
public class CreateNotificationChangesAd {
    private final JavaMailSender emailSender;
    private final Configuration emailConfig;


    @Autowired
    public CreateNotificationChangesAd(JavaMailSender emailSender,
                                        @Qualifier("freeMarker")Configuration emailConfig){
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
    }

    public MimeMessage createNotificationTemplate(Notification notification) throws MessagingException, IOException, TemplateException {
        Map<String,String> model = new HashMap();
        model.put("reason" , notification.getSubject());
        model.put("linkprofile",notification.getSendTo().getResources_link());
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
    public Notification getNotificationFromData(){
        User user = new User();
        user.setEmail("udizsumy@gmail.com");
        user.setResources_link("#");

        Notification notification = new Notification();
        notification.setSendTo(user);
        notification.setEvent(EventTypes.CHANGES_ADVERTISEMENT.name());
        notification.setSubject("Changes AD ");
        notification.setDescription("Your account is ban till 22-103-2021 15:00");

        return notification;
    }


}
