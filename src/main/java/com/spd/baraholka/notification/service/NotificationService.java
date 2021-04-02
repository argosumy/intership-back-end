package com.spd.baraholka.notification.service;

import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.mapper.NotificationMapperFactory;
import com.spd.baraholka.notification.model.BaseNotification;
import com.spd.baraholka.notification.model.Notification;
import com.spd.baraholka.notification.repository.NotificationRepository;
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

@Service
public class NotificationService {

    private final JavaMailSender emailSender;
    private final Configuration emailConfig;
    private final NotificationRepository notificationRepository;
    private final UserService userService;
    private final NotificationMapperFactory notificationMapperFactory;

    public NotificationService(JavaMailSender emailSender,
                               @Qualifier("freeMarker") Configuration emailConfig,
                               NotificationRepository notificationRepository,
                               UserService userService, NotificationMapperFactory notificationMapperFactory) {
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
        this.notificationRepository = notificationRepository;
        this.userService = userService;
        this.notificationMapperFactory = notificationMapperFactory;
    }

    public void sendMessage(BaseNotification baseNotification) throws MessagingException, IOException, TemplateException {
        Map<String, String> model = getModel(baseNotification);
        var templateLabel = baseNotification.getEventType().getTemplateLabel();
        Template template = emailConfig.getTemplate(templateLabel);
        String html = getHtml(model, template);
        MimeMessage message = getMimeMessage(baseNotification, html);

        emailSender.send(message);
    }

    private MimeMessage getMimeMessage(BaseNotification baseNotification, String html) throws MessagingException {
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        mimeMessageHelper.setTo(baseNotification.getMailTo());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(baseNotification.getSubject());
        mimeMessageHelper.setFrom("Admin");
        return message;
    }

    private String getHtml(Map<String, String> model, Template template) throws IOException, TemplateException {
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }

    public int saveNotification(Notification notification) {
        return notificationRepository.saveNotification(notification);
    }

    public void sendAllUsersNotification(Advertisement advertisement) {

        userService.getAllUsers()
                .forEach(userMailTo -> {
                    try {
                        sendMessage(notificationMapperFactory.getNotification(EventType.NEW_ADVERTISEMENT, advertisement, userMailTo));
                    } catch (MessagingException | IOException | TemplateException e) {
                        e.printStackTrace();
                    }
                });

//        userService.getAllUsers().stream()
//                .map(UserShortViewDTO::getEmail)
//                .peek(baseNotification::setMailTo)
//                .forEach(email -> {
//                    try {
//                        sendMessage(baseNotification);
//                    } catch (MessagingException | IOException | TemplateException ignored) {
//                    }
//                });
    }
}
