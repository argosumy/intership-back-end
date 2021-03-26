//package com.spd.baraholka.notification.services;
//
//import com.spd.baraholka.notification.enumes.EventTypes;
//import com.spd.baraholka.notification.model.BaseNotification;
//import com.spd.baraholka.notification.model.CommentNotification;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class AdvertisementChangesNotification implements CreateNotification {
//    private final JavaMailSender emailSender;
//    private final Configuration emailConfig;
//    private final EventTypes types = EventTypes.CHANGES_ADVERTISEMENT;
//
//    @Autowired
//    public AdvertisementChangesNotification(JavaMailSender emailSender,
//                                            @Qualifier("freeMarker")Configuration emailConfig) {
//        this.emailSender = emailSender;
//        this.emailConfig = emailConfig;
//    }
//
//    @Override
//    public EventTypes getType() {
//        return types;
//    }
//
//    @Override
//    public MimeMessage createNotificationTemplate(BaseNotification notif) throws MessagingException, IOException, TemplateException {
//        CommentNotification notificationAd = (CommentNotification) notif;
//        Map<String, String> model = new HashMap();
//        model.put("reason", notificationAd.getDescription());
//        model.put("link_profile", notificationAd.getUserProfileLink());
//        model.put("link_ad", notificationAd.getAdLink());
//        MimeMessage message = this.emailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//        Template template = emailConfig.getTemplate("wishlist-change.ftl");
//        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
//        mimeMessageHelper.setTo(notificationAd.getSendTo());
//        mimeMessageHelper.setText(html, true);
//        mimeMessageHelper.setSubject(notificationAd.getSubject());
//        mimeMessageHelper.setFrom("Admin");
//        return message;
//    }
//}
