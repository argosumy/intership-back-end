package com.spduniversity.notifications.services.factory;

import com.spduniversity.notifications.enumes.EventTypes;
import com.spduniversity.notifications.services.*;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class NotificationFactory {
    private CreateNotificationAccountBan createNotificationAccountBan;
    private CreateNotificationAdvertisementBlock createNotificationAdvertisementBlock;
    private CreateNotificationNewAd createNotificationNewAd;
    private CreateNotificationNewCommentsAd createNotificationNewCommentsAd;
    private CreateNotificationChangesAd createNotificationChangesAd;
    MimeMessage mimeMessage;

    @Autowired
    public NotificationFactory(CreateNotificationAccountBan createNotificationAccountBan,
                               CreateNotificationAdvertisementBlock createNotificationAdvertisementBlock,
                               CreateNotificationNewAd createNotificationNewAd,
                               CreateNotificationNewCommentsAd createNotificationNewCommentsAd,
                               CreateNotificationChangesAd createNotificationChangesAd) {
        this.createNotificationAccountBan = createNotificationAccountBan;
        this.createNotificationAdvertisementBlock = createNotificationAdvertisementBlock;
        this.createNotificationNewAd = createNotificationNewAd;
        this.createNotificationNewCommentsAd = createNotificationNewCommentsAd;
        this.createNotificationChangesAd = createNotificationChangesAd;
    }

    public MimeMessage buildNotification(EventTypes type) throws MessagingException, IOException, TemplateException {
        if (type == EventTypes.ACCOUNT_BAN) {
            mimeMessage =createNotificationAccountBan
                    .createNotificationTemplate(createNotificationAccountBan.getNotificationFromData());
            return mimeMessage;
        }

        if (type == EventTypes.NEW_ADVERTISEMENT) {
            mimeMessage =createNotificationNewAd
                    .createNotificationTemplate(createNotificationNewAd.getNotificationFromData());
            return mimeMessage;
        }
        if (type == EventTypes.ADVERTISEMENT_BLOCK) {
            mimeMessage = createNotificationAdvertisementBlock
                    .createNotificationTemplate(createNotificationAdvertisementBlock.getNotificationFromData());
            return mimeMessage;
        }
        if (type == EventTypes.CHANGES_ADVERTISEMENT) {
            mimeMessage = createNotificationChangesAd.createNotificationTemplate(createNotificationChangesAd
                    .getNotificationFromData());
            return mimeMessage;

        }
        if (type == EventTypes.NEW_COMMENTS_ADVERTISEMENT) {
            mimeMessage = createNotificationNewCommentsAd.createNotificationTemplate(createNotificationNewCommentsAd
                    .getNotificationFromData());
            return mimeMessage;
        }

        if (type == EventTypes.NEW_COMMENTS_TO_MY_COMMENTS) {
        }
        if (type == EventTypes.NEW_MESSAGE_DIRECT) {
        }
        return null;
    }




}
