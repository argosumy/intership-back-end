package com.spd.baraholka.comments.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdatedCommentDto {

    @NotBlank(message = "body shouldn't be empty!")
    @Size(min = 2, max = 255, message = "length must be between 2 and 255 characters!")
    private String body;

    public String getBody() {
        return body;
    }
}
