package com.spd.baraholka.notification.service;

import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.model.BaseNotification;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.spd.baraholka.notification.factory.ModelFactory.getModel;
import static com.spd.baraholka.notification.factory.NotificationFactory.getNotification;
import static com.spd.baraholka.notification.factory.TemplateFactory.template;

public class NotificationService {

    private final JavaMailSender emailSender;
    private final Configuration emailConfig;

    public NotificationService(JavaMailSender emailSender, Configuration emailConfig) {
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
    }

    public MimeMessage createMessage(EventType eventType) throws MessagingException, IOException, TemplateException {
        BaseNotification notification = getNotification(eventType);
        Map<String, String> model = getModel(notification, eventType);
        Template template = emailConfig.getTemplate(template(eventType));
        String html = getHtml(model, template);

        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        mimeMessageHelper.setTo(notification.getSendTo());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(notification.getSubject());
        mimeMessageHelper.setFrom("Admin");

        return message;
    }

    public void sendMessage(EventType eventType) throws MessagingException, IOException, TemplateException {
        emailSender.send(createMessage(eventType));
    }

    private String getHtml(Map<String, String> model, Template template) throws IOException, TemplateException {
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }
}
