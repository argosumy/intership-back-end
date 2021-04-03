package com.spd.baraholka.notification.service;

import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.comments.services.CommentService;
import com.spd.baraholka.config.exceptions.NotificationSendException;
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
import org.springframework.mail.MailException;
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
    private final NotificationMapperFactory notificationMapperFactory;
    private final UserService userService;
    private final AdvertisementService advertisementService;
    private final CommentService commentService;

    public NotificationService(JavaMailSender emailSender,
                               @Qualifier("freeMarker") Configuration emailConfig,
                               NotificationRepository notificationRepository,
                               UserService userService, NotificationMapperFactory notificationMapperFactory,
                               AdvertisementService advertisementService, CommentService commentService) {
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
        this.notificationRepository = notificationRepository;
        this.userService = userService;
        this.notificationMapperFactory = notificationMapperFactory;
        this.advertisementService = advertisementService;
        this.commentService = commentService;
    }

    public void sendMessage(BaseNotification baseNotification) throws MessagingException, IOException, TemplateException {
        try {
            Map<String, String> model = getModel(baseNotification);
            var templateLabel = baseNotification.getEventType().getTemplateLabel();
            Template template = emailConfig.getTemplate(templateLabel);
            String html = getHtml(model, template);
            MimeMessage message = getMimeMessage(baseNotification, html);

            emailSender.send(message);
        } catch (IOException | TemplateException | MessagingException | MailException e) {
            throw new NotificationSendException(baseNotification.getEventType().name());
        }
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
    }

    public void sendAdvertisementCommentNotification(Comment comment) {
        var advertisement = advertisementService.getAdvertisementById(comment.getAdvertisement().getAdvertisementId());
        var user = userService.getUserById(advertisement.getAdvertisementId());

        try {
            sendMessage(notificationMapperFactory.getNotification(EventType.NEW_ADVERTISEMENT_COMMENT, advertisement, user, comment));
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public void sendCommentNotification(Comment comment) {
        var parentComment = commentService.findById(comment.getParent().getId());
        var user = userService.getUserById(comment.getUser().getId());

        if (parentComment.isPresent() && parentComment.get().getId() != 0) {
            try {
                sendMessage(notificationMapperFactory.getNotification(EventType.NEW_COMMENT_ON_COMMENT, null, user, parentComment));
            } catch (MessagingException | IOException | TemplateException e) {
                e.printStackTrace();
            }
        }
    }
}
