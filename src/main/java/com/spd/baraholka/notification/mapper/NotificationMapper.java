package com.spd.baraholka.notification.mapper;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.notification.dto.NotificationDto;
import com.spd.baraholka.notification.model.BaseNotification;
import com.spd.baraholka.user.controller.dto.UserDTO;

import java.time.LocalDateTime;

public class NotificationMapper {

//    public BaseNotification getBaseNotification(NotificationDto notificationDto) {
////        int userId, int advertisementId, int CommentId, String adLink, String userProfileLink, String date
//        BaseNotification notification = new BaseNotificationDto();
//
//        final UserDTO userById = userService.getUserById(userId);
//        notification.setSendToOwner(userById.getEmail());
//        notification.setNameWriter(userById.getFirstName());
//        notification.setUserProfileLink(userProfileLink);
//
//        Advertisement advertisement = advertisementService.findDraftAdById(advertisementId).orElseThrow();
//        notification.setSubject(advertisement.getTitle());
//        notification.setDescription(advertisement.getDescription());
//        notification.setAdLink(adLink);
//        notificationDto.setBanDate(LocalDateTime.now());
////        notificationDto.s
//
//        return notification;
//    }

}
