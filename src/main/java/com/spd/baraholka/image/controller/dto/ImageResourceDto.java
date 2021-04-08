package com.spd.baraholka.image.controller.dto;

import javax.validation.constraints.*;

public final class ImageResourceDto {

    @Positive
    private final int id;
    @Positive
    private final int adId;
    private final boolean primary;
    @Min(1) @Max(10)
    private final int position;
    @NotBlank
    private final String url;

    public ImageResourceDto(int id, int adId, boolean primary, int position, String url) {
        this.id = id;
        this.adId = adId;
        this.primary = primary;
        this.position = position;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public int getAdId() {
        return adId;
    }

    public boolean isPrimary() {
        return primary;
    }

    public int getPosition() {
        return position;
    }

    public String getUrl() {
        return url;
    }
}
