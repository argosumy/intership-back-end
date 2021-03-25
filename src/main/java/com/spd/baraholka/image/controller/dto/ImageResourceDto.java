package com.spd.baraholka.image.controller.dto;

public final class ImageResourceDto {

    private final long id;
    private final long adId;
    private final boolean isPrimary;
    private final int position;
    private final String url;

    public ImageResourceDto(long id, long adId, boolean isPrimary, int position, String url) {
        this.id = id;
        this.adId = adId;
        this.isPrimary = isPrimary;
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
        return isPrimary;
    }

    public int getPosition() {
        return position;
    }

    public String getUrl() {
        return url;
    }
}
