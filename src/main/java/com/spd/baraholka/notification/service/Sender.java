package com.spd.baraholka.notification.service;

import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.comments.entities.Comment;
import org.springframework.stereotype.Service;

@Service
public interface Sender {

    void sendAllUsersNotification(Advertisement advertisement);

    void sendAdvertisementCommentNotification(Comment comment);

    void sendCommentNotification(Comment comment);

    void sendWishListChangeNotification(Advertisement advertisement);
}
