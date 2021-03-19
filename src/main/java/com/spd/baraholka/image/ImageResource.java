package com.spd.baraholka.image;

import org.springframework.web.multipart.MultipartFile;

public class ImageResource {

    private long id;
    private final long adId;
    private final boolean isPrimary;
    private final int position;
    private MultipartFile image;
    private String imageUrl;

    private ImageResource(long adId, boolean isPrimary, int position, MultipartFile image) {
        this.adId = adId;
        this.isPrimary = isPrimary;
        this.position = position;
        this.image = image;
    }

    public ImageResource(long id, long adId, boolean isPrimary, int position, String imageUrl) {
        this.id = id;
        this.adId = adId;
        this.isPrimary = isPrimary;
        this.position = position;
        this.imageUrl = imageUrl;
    }

    public static ImageResource of(long adId, boolean isPrimary, int positionOrder, MultipartFile image) {
        return new ImageResource(adId, isPrimary, positionOrder, image);
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getAdId() {
        return adId;
    }

    public boolean getIsPrimary() {
        return isPrimary;
    }

    public int getPosition() {
        return position;
    }

    public MultipartFile getImage() {
        return image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
