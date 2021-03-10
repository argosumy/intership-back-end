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
public class CreateNotificationAdvertisementBlock {
    private JavaMailSender emailSender;
    private Configuration emailConfig;
    @Autowired
    public CreateNotificationAdvertisementBlock(JavaMailSender emailSender, @Qualifier("freeMarker") Configuration emailConfig) {
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
    }

    public MimeMessage createNotificationTemplate(Notification notification) throws MessagingException, IOException, TemplateException {
        String dateBlock = "15-10-2020 15:00";
        Map<String,String> model = new HashMap();
        model.put("ad_name", notification.getSendTo().getFirst_name());
        model.put("block_ends" , dateBlock);
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Template template = emailConfig.getTemplate("ad-block.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        mimeMessageHelper.setTo(notification.getSendTo().getEmail());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject("Advertisement Block");
        mimeMessageHelper.setFrom("Admin");

        return message;
    }

    public Notification getNotificationFromData(){
        User user = new User();
        user.setFirst_name("Valeriy");
        user.setEmail("udizsumy@gmail.com");
        Notification notification = new Notification();
        notification.setEvent(EventTypes.ADVERTISEMENT_BLOCK.name());
        notification.setSubject("Advertisement Block");
        notification.setSendTo(user);
        notification.setDescription("Your advertisement is ban till 22-103-2021 15:00");

        return notification;
    }
}