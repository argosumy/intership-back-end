package com.spd.baraholka.notification.service;

import com.spd.baraholka.advertisement.controller.dto.FullAdvertisementDTO;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.comments.dto.CommentUserInfoDto;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.comments.mappers.CommentUserInfoDtoMapper;
import com.spd.baraholka.comments.services.CommentService;
import com.spd.baraholka.image.persistance.entity.ImageResource;
import com.spd.baraholka.image.service.ImageServiceImpl;
import com.spd.baraholka.notification.enums.EventType;
import com.spd.baraholka.notification.mapper.NotificationMapperFactory;
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
    private final CommentUserInfoDtoMapper commentUserInfoDtoMapper;

    public NotificationSender(NotificationMapperFactory notificationMapperFactory, UserService userService,
                              AdvertisementService advertisementService, CommentService commentService,
                              NotificationService notificationService, ImageServiceImpl imageService, UserMapper userMapper,
                              CommentUserInfoDtoMapper commentUserInfoDtoMapper) {
        this.notificationMapperFactory = notificationMapperFactory;
        this.userService = userService;
        this.advertisementService = advertisementService;
        this.commentService = commentService;
        this.notificationService = notificationService;
        this.imageService = imageService;
        this.userMapper = userMapper;
        this.commentUserInfoDtoMapper = commentUserInfoDtoMapper;
    }

    @Override
    public void sendAllUsersNotification(FullAdvertisementDTO advertisement) {
        var imageResources = imageService.getAllByAdId(advertisement.getAdvertisementId());
        var imageUrlsList = imageResources.stream().map(ImageResource::getImageUrl).collect(Collectors.toList());
        var mainImage = imageService.getPrimary(Collections.singletonList((long) advertisement.getAdvertisementId())).get(0).getImageUrl();

        userService.getAllUsers()
                .forEach(userMailTo -> notificationService.sendMessage(notificationMapperFactory.getNotification(
                        EventType.NEW_ADVERTISEMENT, advertisement, userMailTo, NULL, imageUrlsList, mainImage)));
    }

    @Override
    public void sendAdvertisementCommentNotification(CommentUserInfoDto comment) {
        var advertisement = advertisementService.getAdvertisementById(comment.getAdvertisementId());
        var user = userMapper.toShortViewDTO(userService.getUserById(advertisement.getAdvertisementOwner().getId()));

        notificationService.sendMessage(notificationMapperFactory.getNotification(EventType.NEW_ADVERTISEMENT_COMMENT, advertisement, user, comment, NULL, NULL));
    }

    @Override
    public void sendCommentNotification(CommentUserInfoDto comment) {
        var parentComment = commentService.findById(comment.getParentCommentId());
        if (parentComment.isPresent() && parentComment.get().getId() != 0) {
            var user = userMapper.toShortViewDTO(userService.getUserById(parentComment.get().getUser().getId()));

            notificationService.sendMessage(notificationMapperFactory.getNotification(EventType.NEW_COMMENT_ON_COMMENT, NULL, user, comment, NULL, NULL));
        }
    }

    @Override
    public void sendWishListChangeNotification(Advertisement advertisement) {
    }
}
