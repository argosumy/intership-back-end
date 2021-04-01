package com.spd.baraholka.user.controller.dto;

import com.spd.baraholka.annotation.advertisement.PresentOrFutureDate;
import com.spd.baraholka.annotation.user.UserExist;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class BanDetailDTO {

    @NotNull
    @UserExist
    private int userId;

    @PresentOrFutureDate
    private LocalDateTime banedUntil;

    @NotEmpty
    @Size(max = 200)
    private String reason;

    @NotNull
    private boolean isNotify;
}
