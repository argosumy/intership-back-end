package com.spduniversity.notifications.services.factory;

import com.spduniversity.notifications.enumes.EventTypes;
import com.spduniversity.notifications.services.CreateNotificationAccountBan;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class NotificationFactory {
    private CreateNotificationAccountBan createNotificationAccountBan;
    MimeMessage mimeMessage;
    @Autowired
    public NotificationFactory(CreateNotificationAccountBan createNotificationAccountBan) {
        this.createNotificationAccountBan = createNotificationAccountBan;
    }

    public MimeMessage buildNotification(EventTypes type) throws MessagingException, IOException, TemplateException {
        if (type == EventTypes.ACCOUNT_BAN) {
            mimeMessage =createNotificationAccountBan.createNotificationTemplate();
            return mimeMessage;
        }

        if (type == EventTypes.NEW_ADVERTISEMENT) {

        }
        if (type == EventTypes.ADVERTISEMENT_BLOCK) {
        }
        if (type == EventTypes.CHANGES_ADVERTISEMENT) {
        }
        if (type == EventTypes.NEW_COMMENTS_ADVERTISEMENT) {
        }
        if (type == EventTypes.NEW_COMMENTS_TO_MY_COMMENTS) {
        }
        if (type == EventTypes.NEW_MESSAGE_DIRECT) {
        }
        return null;
    }




}
