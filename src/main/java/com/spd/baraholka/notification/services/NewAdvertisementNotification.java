//package com.spd.baraholka.notification.services;
//
//import com.spd.baraholka.notification.model.CommentNotification;
//import com.spd.baraholka.notification.model.BaseNotification;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class NewAdvertisementNotification implements CreateNotification {
//    private final JavaMailSender emailSender;
//    private final Configuration emailConfig;
//    private final EventTypes types = EventTypes.NEW_ADVERTISEMENT;
//
//    @Autowired
//    public NewAdvertisementNotification(JavaMailSender emailSender, @Qualifier("freeMarker") Configuration configuration) {
//        this.emailSender = emailSender;
//        this.emailConfig = configuration;
//    }
//
//    @Override
//    public EventTypes getType() {
//        return types;
//    }
//
//    @Override
//    public MimeMessage createNotificationTemplate(BaseNotification not) throws MessagingException, IOException, TemplateException {
//        CommentNotification notification = (CommentNotification) not;
//        Map<String, Object> model = new HashMap<>();
//        model.put("main_image", notification.getMainImage());
//        model.put("images", notification.getImages());
//        model.put("send_to_owner", notification.getOwnerName());
//        model.put("title", "");
//        model.put("description", notification.getDescription());
//        model.put("profile_link", notification.getUserProfileLink());
//        model.put("link_ad", notification.getAdLink());
//
//        MimeMessage message = this.emailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//        Template template = emailConfig.getTemplate("advertisement-new.ftl");
//        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
//
//        mimeMessageHelper.setText(html, true);
//        mimeMessageHelper.setTo(notification.getSendTo());
//        mimeMessageHelper.setSubject("NEW ADVERTISEMENT " + notification.getAdName() + " price "
//                + notification.getPrice()
//                + notification.getCurrency());
//        mimeMessageHelper.setFrom("Admin");
//        return message;
//    }
//}