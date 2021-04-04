package com.spd.baraholka.notification.service;

import com.spd.baraholka.advertisement.controller.dto.FullAdvertisementDTO;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.comments.dto.CommentUserInfoDto;
import com.spd.baraholka.comments.entities.Comment;
import org.springframework.stereotype.Service;

@Service
public interface Sender {

    void sendAllUsersNotification(FullAdvertisementDTO advertisement);

    void sendAdvertisementCommentNotification(CommentUserInfoDto comment);

    void sendCommentNotification(CommentUserInfoDto comment);

    void sendWishListChangeNotification(Advertisement advertisement);
}
