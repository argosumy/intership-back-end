package com.spduniversity.notifications.services;
import com.spduniversity.notifications.enumes.EventTypes;
import com.spduniversity.notifications.model.NotificationAdvertisement;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CreateNotificationNewAd implements CreateNotification {
    private final JavaMailSender emailSender;
    private final Configuration emailConfig;
    private final EventTypes types = EventTypes.NEW_ADVERTISEMENT;

    @Autowired
    public CreateNotificationNewAd (JavaMailSender emailSender,
                  @Qualifier("freeMarker")Configuration configuration){
        this.emailSender = emailSender;
        this.emailConfig = configuration;
    }

    @Override
    public EventTypes getType() {
        return types;
    }

    @Override
    public MimeMessage createNotificationTemplate(Notification notification) throws MessagingException, IOException, TemplateException {
        NotificationAdvertisement notif = (NotificationAdvertisement) notification;
        Map<String, Object> model = new HashMap<>();
        model.put("main_image",notif.getMainImage());
        model.put("images",notif.getImages());
        model.put("recipient", notif.getSendFrom().getEmail());
        model.put("title", "");
        model.put("description", notif.getDescription());
        model.put("profile_link",notification.getSendTo().getResourcesLink());
        model.put("link_ad",notif.getLinkAd());

        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Template template = emailConfig.getTemplate("new-Ad.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setTo(notification.getSendTo().getEmail());
        mimeMessageHelper.setSubject("NEW ADVERTISEMENT " + notif.getNameAd() + " price "
                + notif.getPrice()
                + notif.getCurrency());
        mimeMessageHelper.setFrom("Admin");
        return message;
    }

    //test method
    @Override
    public Notification getNotificationFromData(){
        NotificationAdvertisement notification = new NotificationAdvertisement();

        List<String> images = new ArrayList<>();
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTceb6oPjt7_NqPOKCbBXlofFQMeZU6DpRAEw&usqp=CAU");
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQI7nmkdLHf3O9rwsJecyD5ppwFI0D2q-sMQ&usqp=CAU");
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQI7nmkdLHf3O9rwsJecyD5ppwFI0D2q-sMQ&usqp=CAU");
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQI7nmkdLHf3O9rwsJecyD5ppwFI0D2q-sMQ&usqp=CAU");
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQI7nmkdLHf3O9rwsJecyD5ppwFI0D2q-sMQ&usqp=CAU");


        User userTo = new User();
        userTo.setId(2);
        userTo.setEmail("udizsumy@gmail.com");
        userTo.setResourcesLink("#");

        User userFrom = new User();
        userFrom.setId(1);
        userFrom.setEmail("argosumy@gmail.com");

        notification.setNameAd("Note");
        notification.setPrice(100);
        notification.setCurrency("USD");
        notification.setMainImage("http://s020.radikal.ru/i714/1512/ca/4b42d3dfc4c8.jpg");
        notification.setLocation("#");
        notification.setLinkAd("#");
        notification.setDescription("Description ++++++");
        notification.setImages(images);

        notification.setEvent(EventTypes.NEW_ADVERTISEMENT.name());
        notification.setSubject("Account BAN");
        notification.setSendTo(userTo);
        notification.setSendFrom(userFrom);


        return notification;
    }


}
