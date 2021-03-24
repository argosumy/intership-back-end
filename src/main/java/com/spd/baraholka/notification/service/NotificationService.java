package com.spd.baraholka.notification.service;

import com.spd.baraholka.advertisements.services.AdvertisementService;
import com.spd.baraholka.notification.dto.NotificationDto;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.BaseNotification;
import com.spd.baraholka.user.service.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.spd.baraholka.notification.factory.ModelFactory.getModel;
import static com.spd.baraholka.notification.factory.NotificationFactory.getNotification;
import static com.spd.baraholka.notification.factory.TemplateFactory.template;

@Service
public class NotificationService {

    private final JavaMailSender emailSender;
    private final Configuration emailConfig;
    private final UserService userService;
    private final AdvertisementService advertisementService;

    public NotificationService(JavaMailSender emailSender, @Qualifier("freeMarker") Configuration emailConfig, UserService userService,
                               AdvertisementService advertisementService) {
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
        this.userService = userService;
        this.advertisementService = advertisementService;
    }

    public MimeMessage createMessage(NotificationDto notificationDto, EventType eventType) throws MessagingException, IOException, TemplateException {
//        BaseNotification notification = getNotification(notificationDto, eventType);
        Map<String, String> model = getModel(notificationDto, eventType);
        Template template = emailConfig.getTemplate(template(eventType));
        String html = getHtml(model, template);

        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        mimeMessageHelper.setTo(notificationDto.getSendTo());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(notificationDto.getSubject());
        mimeMessageHelper.setFrom("Admin");

        return message;
    }

    public void sendMessage(NotificationDto notificationDto, EventType eventType) throws MessagingException, IOException, TemplateException {
        emailSender.send(createMessage(notificationDto, eventType));
    }

    private String getHtml(Map<String, String> model, Template template) throws IOException, TemplateException {
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }
}
