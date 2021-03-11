package com.spduniversity.notifications.conrollers;

import com.spduniversity.notifications.enumes.EventTypes;
import com.spduniversity.notifications.services.TestNew;
import com.spduniversity.notifications.services.factory.NotificationFactory;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.internet.MimeMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.MessagingException;
import java.io.IOException;

@RestController
public class MainControllers {
    private NotificationFactory factory;
    private JavaMailSender sender;


    @Autowired
    public MainControllers(NotificationFactory factory,
                           JavaMailSender sender) {
        this.factory = factory;
        this.sender = sender;

    }

    @GetMapping("/ban")
    public String getBan() throws MessagingException, IOException, TemplateException {
        MimeMessage mailMessage = factory.buildNotification(EventTypes.NEW_ADVERTISEMENT);
        sender.send(mailMessage);

        return "OK";
    }
}
