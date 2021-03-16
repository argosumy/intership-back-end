package com.spduniversity.image.controller;

public class ImageResourceDto {

    private long id;
    private final long adId;
    private final boolean isPrimary;
    private final int positionOrder;
    private final String imageUrl;

    private ImageResourceDto(long id, long adId, boolean isPrimary, int positionOrder, String imageUrl) {
        this.id = id;
        this.adId = adId;
        this.isPrimary = isPrimary;
        this.positionOrder = positionOrder;
        this.imageUrl = imageUrl;
    }

    public static ImageResourceDto of(long id, long adId, boolean isPrimary, int positionOrder, String imageUrl) {
        return new ImageResourceDto(id, adId, isPrimary, positionOrder, imageUrl);
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

    public int getPositionOrder() {
        return positionOrder;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
