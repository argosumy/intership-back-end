package com.spduniversity.notifications.services;
import com.spduniversity.notifications.enumes.EventTypes;
import com.spduniversity.notifications.model.Advertisemenets;
import com.spduniversity.notifications.model.Notification;
import com.spduniversity.notifications.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CreateNotificationNewAd {
    private final SpringTemplateEngine templateEngine;
    private final JavaMailSender emailSender;

    @Autowired
    public CreateNotificationNewAd(JavaMailSender emailSender,
                                   SpringTemplateEngine templateEngine){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    public MimeMessage createNotificationTemplate(Notification notification) throws MessagingException {
        Advertisemenets notif = (Advertisemenets) notification;
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("main_image",notif.getMainImage());
        templateModel.put("images",notif.getImages());
        templateModel.put("recipient", notif.getSendFrom().getEmail());
        templateModel.put("title", "");
        templateModel.put("description", notif.getDescription());
        templateModel.put("profile_link",notification.getSendTo().getResources_link());

        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        String html = templateEngine.process("new-Ad", thymeleafContext);

        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setTo(notification.getSendTo().getEmail());
        mimeMessageHelper.setSubject("NEW ADVERTISEMENT " + notif.getNameAd() + " price "
                                                          + notif.getPrice()
                                                          + notif.getCurrency());
        return message;
    }

    //test method
    public Notification getNotificationFromData(){
        Advertisemenets notification = new Advertisemenets();

        List<String> images = new ArrayList<>();
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTceb6oPjt7_NqPOKCbBXlofFQMeZU6DpRAEw&usqp=CAU");
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQI7nmkdLHf3O9rwsJecyD5ppwFI0D2q-sMQ&usqp=CAU");
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQI7nmkdLHf3O9rwsJecyD5ppwFI0D2q-sMQ&usqp=CAU");
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQI7nmkdLHf3O9rwsJecyD5ppwFI0D2q-sMQ&usqp=CAU");
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQI7nmkdLHf3O9rwsJecyD5ppwFI0D2q-sMQ&usqp=CAU");


        User userTo = new User();
        userTo.setId(2);
        userTo.setEmail("udizsumy@gmail.com");
        userTo.setResources_link("#");

        User userFrom = new User();
        userFrom.setId(1);
        userFrom.setEmail("argosumy@gmail.com");

        notification.setNameAd("Note");
        notification.setPrice(100);
        notification.setCurrency("USD");
        notification.setMainImage("http://s020.radikal.ru/i714/1512/ca/4b42d3dfc4c8.jpg");
        notification.setLocation("#");
        notification.setDescription("Description ++++++");
        notification.setImages(images);

        notification.setEvent(EventTypes.NEW_ADVERTISEMENT.name());
        notification.setSubject("Account BAN");
        notification.setSendTo(userTo);
        notification.setSendFrom(userFrom);


        return notification;
    }






}
