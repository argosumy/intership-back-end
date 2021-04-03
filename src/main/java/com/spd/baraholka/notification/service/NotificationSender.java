package com.spd.baraholka.notification.service;

import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.comments.services.CommentService;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.mapper.NotificationMapperFactory;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationSender implements Sender {

    private final NotificationMapperFactory notificationMapperFactory;
    private final UserService userService;
    private final AdvertisementService advertisementService;
    private final CommentService commentService;
    private final NotificationService notificationService;

    public NotificationSender(NotificationMapperFactory notificationMapperFactory, UserService userService,
                              AdvertisementService advertisementService, CommentService commentService,
                              NotificationService notificationService) {
        this.notificationMapperFactory = notificationMapperFactory;
        this.userService = userService;
        this.advertisementService = advertisementService;
        this.commentService = commentService;
        this.notificationService = notificationService;
    }

    @Override
    public void sendAllUsersNotification(Advertisement advertisement) {
//        List<UserShortViewDTO> allUsers = userService.getAllUsers();
//        for (UserShortViewDTO allUser : allUsers) {
//            notificationService.sendMessage(notificationMapperFactory.getNotification(
//                    EventType.NEW_ADVERTISEMENT, advertisement, allUser, null));
//        }
        userService.getAllUsers()
                .forEach(userMailTo -> notificationService.sendMessage(notificationMapperFactory.getNotification(
                        EventType.NEW_ADVERTISEMENT, advertisement, userMailTo, null)));
    }

    @Override
    public void sendAdvertisementCommentNotification(Comment comment) {
        var advertisement = advertisementService.getAdvertisementById(comment.getAdvertisement().getAdvertisementId());
        var user = userService.getUserById(advertisement.getAdvertisementId());

        notificationService.sendMessage(notificationMapperFactory.getNotification(EventType.NEW_ADVERTISEMENT_COMMENT, advertisement, user, comment));
    }

    @Override
    public void sendCommentNotification(Comment comment) {
        var parentComment = commentService.findById(comment.getParent().getId());
        if (parentComment.isPresent() && parentComment.get().getId() != 0) {
            var user = userService.getUserById(parentComment.get().getUser().getId());

            notificationService.sendMessage(notificationMapperFactory.getNotification(EventType.NEW_COMMENT_ON_COMMENT, null, user, comment));
        }
    }

    @Override
    public void sendWishListChangeNotification(Advertisement advertisement) {
    }
}
