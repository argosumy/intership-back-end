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
public class CreateNotificationNewAd implements CreateNotification {
    private final JavaMailSender emailSender;
    private final Configuration emailConfig;
    private final EventTypes types = EventTypes.NEW_ADVERTISEMENT;

    @Autowired
    public CreateNotificationNewAd(JavaMailSender emailSender, @Qualifier("freeMarker")Configuration configuration) {
        this.emailSender = emailSender;
        this.emailConfig = configuration;
    }

    @Override
    public EventTypes getType() {
        return types;
    }

    @Override
    public MimeMessage createNotificationTemplate(Notification not) throws MessagingException, IOException, TemplateException {
        CommentNotification notification = (CommentNotification) not;
        Map<String, Object> model = new HashMap<>();
        model.put("main_image", notification.getMainImage());
        model.put("images", notification.getImages());
        model.put("send_to_owner", notification.getSendToOwner());
        model.put("title", "");
        model.put("description", notification.getDescription());
        model.put("profile_link", notification.getProfileLinkUser());
        model.put("link_ad", notification.getLinkAd());

        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Template template = emailConfig.getTemplate("new-Ad.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setTo(notification.getSendTo());
        mimeMessageHelper.setSubject("NEW ADVERTISEMENT " + notification.getNameAd() + " price "
                + notification.getPrice()
                + notification.getCurrency());
        mimeMessageHelper.setFrom("Admin");
        return message;
    }
}