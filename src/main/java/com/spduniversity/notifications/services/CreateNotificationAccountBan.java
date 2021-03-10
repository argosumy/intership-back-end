package com.spduniversity.notifications.services;


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
public class CreateNotificationAccountBan{
    private JavaMailSender emailSender;
    private Configuration emailConfig;



    @Autowired
    public CreateNotificationAccountBan(JavaMailSender emailSender,
                                        @Qualifier("freeMarker")Configuration emailConfig){
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
    }

    /*
     * Initial NotificationAccountBan
     * (NotificationAccountBan(String recipient,String title, String reasonToBan, LocalDateTime localDateTime))
     * and
     * Template Builder for sent (MimeMessage createNotificationTemplate(Notification notification) )
     * @param args - String args[0] - email recipient,
     *               String args[1] - title,
     *               String args[2] - reasonToBan,
     *               String args[3] - period ban (DateTime  pattern("dd-MM-yyyy HH:mm"))
     *
     */




    public MimeMessage createNotificationTemplate() throws MessagingException, IOException, TemplateException {
        Notification notification = getNotificationFromData();
        Map<String,String> model = new HashMap();
        model.put("name", "name notification");
        model.put("content", "AccountBAN");
        model.put("description", "Account BAN");
        model.put("block_ends" , "22-10-2020");
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        // Template template = emailConfig.getTemplate("email.ftl");
        Template template = emailConfig.getTemplate("profile-block.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        mimeMessageHelper.setTo("udizsumy@gmail.com");
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject("Account BAN");
        mimeMessageHelper.setFrom("Admin");

        return message;
    }

    private Notification getNotificationFromData(){
        User user = new User();
        user.setEmail("udizsumy@gmail.com");
        Notification notification = new Notification();
        notification.setEvent("Account BAN");
        notification.setSubject("Account BAN");
        notification.setSendTo(user);
        notification.setDescription("Your account is ban till 22-103-2021 15:00");

        return notification;
    }


}
