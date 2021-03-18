package com.spd.baraholka.notification.services;

import com.spd.baraholka.notification.enumes.EventTypes;
import com.spd.baraholka.notification.model.CommentNotification;
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
import java.util.HashMap;
import java.util.Map;

@Service
public class CreateNotificationNewCommentsAd implements CreateNotification {
    private final JavaMailSender emailSender;
    private final Configuration emailConfig;
    @Autowired
    public CreateNotificationNewCommentsAd(JavaMailSender emailSender, @Qualifier("freeMarker")Configuration emailConfig) {
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
    }

    @Override
    public MimeMessage createNotificationTemplate(Notification not) throws MessagingException, IOException, TemplateException {
        CommentNotification notification = (CommentNotification) not;
        Map<String, String> model = new HashMap();
        model.put("writer", notification.getNameWriter());
        model.put("link_profile", notification.getProfileLinkUser());
        model.put("ad", notification.getLinkAd());

        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Template template = emailConfig.getTemplate("new-comment-ad.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        mimeMessageHelper.setTo(notification.getSendTo());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(notification.getSubject());
        mimeMessageHelper.setFrom("Admin");

        return message;
    }

    @Override
    public EventTypes getType() {
        return EventTypes.NEW_COMMENTS_ADVERTISEMENT;
    }
}