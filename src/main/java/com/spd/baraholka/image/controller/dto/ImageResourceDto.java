package com.spd.baraholka.image.controller.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public final class ImageResourceDto {

    @Positive
    private final long id;
    @Positive
    private final long adId;
    private final boolean primary;
    @Min(1) @Max(10)
    private final int position;
    @NotBlank
    private final String url;

    public ImageResourceDto(long id, long adId, boolean primary, int position, String url) {
        this.id = id;
        this.adId = adId;
        this.primary = primary;
        this.position = position;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public long getAdId() {
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
