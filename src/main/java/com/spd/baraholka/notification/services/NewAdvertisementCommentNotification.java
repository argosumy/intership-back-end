//package com.spd.baraholka.notification.services;
//
//import com.spd.baraholka.notification.enumes.EventTypes;
//import com.spd.baraholka.notification.enums.EventType;
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
//import java.util.Map;
//
//import static com.spd.baraholka.notification.factory.ModelFactory.getModel;
//import static com.spd.baraholka.notification.factory.NotificationFactory.getNotification;
//import static com.spd.baraholka.notification.factory.TemplateFactory.template;
//
//@Service
//public class NewAdvertisementCommentNotification implements CreateNotification {
//    private final JavaMailSender emailSender;
//    private final Configuration emailConfig;
//
//    @SuppressWarnings("checkstyle:EmptyLineSeparator")
//    @Autowired
//    public NewAdvertisementCommentNotification(JavaMailSender emailSender, @Qualifier("freeMarker") Configuration emailConfig) {
//        this.emailSender = emailSender;
//        this.emailConfig = emailConfig;
//    }
//
//    public MimeMessage createNotificationTemplate(BaseNotification baseNotification, EventType eventType) throws MessagingException, IOException, TemplateException {
//        BaseNotification notification = getNotification(eventType);
//        Map<String, String> model = getModel(notification, eventType);
//        Template template = emailConfig.getTemplate(template(eventType));
//        String html = getHtml(model, template);
//
//        MimeMessage message = this.emailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//
//        mimeMessageHelper.setTo(baseNotification.getSendTo());
//        mimeMessageHelper.setText(html, true);
//        mimeMessageHelper.setSubject(notification.getSubject());
//        mimeMessageHelper.setFrom("Admin");
//
//        return message;
//    }
//
//    private String getHtml(Map<String, String> model, Template template) throws IOException, TemplateException {
//        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
//    }
//
////    private Map<String, String> getModel(BaseNotification notification, EventType eventType) {
////        Map<String, String> model = new HashMap<>();
////
////        if (eventType == EventType.NEW_COMMENTS_ADVERTISEMENT) {
////            model.put("writer", ((CommentNotification) notification).getNameWriter());
////            model.put("link_profile", notification.getProfileLinkUser());
////            model.put("ad",  ((CommentNotification) notification).getLinkAd());
////            return model;
////        }
////        if (notification instanceof BanBlockNotification)
////
////    }
//
//
////    @Override
////    public MimeMessage createNotificationTemplate(Notification not) throws MessagingException, IOException, TemplateException {
////        CommentNotification notification = (CommentNotification) not;
////        Map<String, String> model = new HashMap();
////        model.put("writer", notification.getNameWriter());
////        model.put("link_profile", notification.getProfileLinkUser());
////        model.put("ad", notification.getLinkAd());
////
////        MimeMessage message = this.emailSender.createMimeMessage();
////        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
////        Template template = emailConfig.template("new-comment-ad.ftl");
////        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
////
////        mimeMessageHelper.setTo(notification.getSendTo());
////        mimeMessageHelper.setText(html, true);
////        mimeMessageHelper.setSubject(notification.getSubject());
////        mimeMessageHelper.setFrom("Admin");
////
////        return message;
////    }
//
//
//    @Override
//    public EventTypes getType() {
//        return EventTypes.NEW_COMMENTS_ADVERTISEMENT;
//    }
//}