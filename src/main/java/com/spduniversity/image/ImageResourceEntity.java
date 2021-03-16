package com.spduniversity.image;

public class ImageResourceEntity {

    private final long id;
    private final long adId;
    private final boolean isPrimary;
    private final String imageUrl;

    private ImageResourceEntity(long id, long adId, boolean isPrimary, String imageUrl) {
        this.id = id;
        this.adId = adId;
        this.isPrimary = isPrimary;
        this.imageUrl = imageUrl;
    }

    public static ImageResourceEntity of(long id, long adId, boolean isPrimary, String imageUrl) {
        return new ImageResourceEntity(id, adId, isPrimary, imageUrl);
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

    public String getImageUrl() {
        return imageUrl;
    }
}
