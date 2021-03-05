package com.spduniversity.entities.comments;

import com.spduniversity.entities.advertisements.Advertisement;
import com.spduniversity.entities.users.User;

import java.time.LocalDate;

public class Comment {

    private int id;
    private String body;
    private LocalDate createdDate;
    private Advertisement advertisement;
    private User user;
}
