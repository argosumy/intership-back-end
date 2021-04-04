package com.spd.baraholka.image.persistance.entity;

import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public class ImageResource {

    private long id;
    private final long adId;
    private final boolean isPrimary;
    private final int position;
    private MultipartFile image;
    private String imageUrl;

    public ImageResource(long adId, boolean isPrimary, int position, MultipartFile image) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageResource that = (ImageResource) o;
        return id == that.id && adId == that.adId && isPrimary == that.isPrimary && position == that.position && Objects.equals(image, that.image) && imageUrl.equals(that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adId, isPrimary, position, image, imageUrl);
    }

    @Override
    public String toString() {
        return "ImageResource{" +
                "id=" + id +
                ", adId=" + adId +
                ", isPrimary=" + isPrimary +
                ", position=" + position +
                ", image=" + image +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
