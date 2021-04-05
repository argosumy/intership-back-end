package com.spd.baraholka.notification.service;

import com.spd.baraholka.advertisement.controller.dto.FullAdvertisementDTO;
import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.comments.dto.CommentUserInfoDto;
import com.spd.baraholka.comments.services.CommentService;
import com.spd.baraholka.image.persistance.entity.ImageResource;
import com.spd.baraholka.image.service.ImageServiceImpl;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.mapper.NotificationMapper;
import com.spd.baraholka.notification.mapper.NotificationMapperFactory;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.controller.mappers.UserMapper;
import com.spd.baraholka.user.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class NotificationSender implements Sender {

    public static final Object NULL = null;
    private final NotificationMapperFactory notificationMapperFactory;
    private final UserService userService;
    private final AdvertisementService advertisementService;
    private final CommentService commentService;
    private final NotificationService notificationService;
    private final ImageServiceImpl imageService;
    private final UserMapper userMapper;
    private final NotificationMapper notificationMapper;
    private EventType eventType;

    @SuppressWarnings("checkstyle:ParameterNumber")
    public NotificationSender(NotificationMapperFactory notificationMapperFactory, UserService userService,
                              AdvertisementService advertisementService, CommentService commentService,
                              NotificationService notificationService, ImageServiceImpl imageService, UserMapper userMapper,
                              NotificationMapper notificationMapper) {
        this.notificationMapperFactory = notificationMapperFactory;
        this.userService = userService;
        this.advertisementService = advertisementService;
        this.commentService = commentService;
        this.notificationService = notificationService;
        this.imageService = imageService;
        this.userMapper = userMapper;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public void sendAllUsersNotification(FullAdvertisementDTO advertisement) {
        var imageResources = imageService.getAllByAdId(advertisement.getAdvertisementId());
        var imageUrlsList = imageResources.stream().map(ImageResource::getImageUrl).collect(Collectors.toList());
        var mainImage = imageService.getPrimary(Collections.singletonList((long) advertisement.getAdvertisementId())).get(0).getImageUrl();

        userService.getAllUsers()
                .forEach(userMailTo -> {
                    notificationService.sendMessage(notificationMapperFactory.getNotification(
                            EventType.NEW_ADVERTISEMENT, advertisement, userMailTo, NULL, imageUrlsList, mainImage));
                    notificationService.saveNotification(notificationMapper.of(userMailTo.getId(), advertisement.getAdvertisementId(), eventType));
                });
    }

    @Override
    public void sendAdvertisementCommentNotification(CommentUserInfoDto comment) {
        var advertisement = advertisementService.getAdvertisementById(comment.getAdvertisementId());
        var user = userMapper.toShortViewDTO(userService.getUserById(advertisement.getAdvertisementOwner().getId()));
        eventType = EventType.NEW_ADVERTISEMENT_COMMENT;

        notificationService.sendMessage(notificationMapperFactory.getNotification(eventType, advertisement, user, comment, NULL, NULL));
        notificationService.saveNotification(notificationMapper.of(user.getId(), advertisement.getAdvertisementId(), eventType));
    }

    @Override
    public void sendCommentNotification(CommentUserInfoDto comment) {
        var parentComment = commentService.findById(comment.getParentCommentId());
        if (parentComment.isPresent() && parentComment.get().getId() != 0) {
            var user = userMapper.toShortViewDTO(userService.getUserById(parentComment.get().getUser().getId()));
            eventType = EventType.NEW_COMMENT_ON_COMMENT;

            notificationService.sendMessage(notificationMapperFactory.getNotification(eventType, NULL, user, comment, NULL, NULL));
            notificationService.saveNotification(notificationMapper.of(user.getId(), 0, eventType));
        }
    }

    @Override
    public void sendWishListChangeNotification(FullAdvertisementDTO advertisement) {
        //TODO, blocked by other task
    }

    @Override
    public void sendUserBanNotification(UserShortViewDTO user) {
        //TODO, blocked by other task
        notificationService.sendMessage(notificationMapperFactory.getNotification(eventType, NULL, user, NULL, NULL, NULL));
    }
}
