package com.spduniversity.image;

import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public class ImageResource {

    private long id;
    private final long adId;
    private final boolean isPrimary;
    private final int positionOrder;
    private MultipartFile image;
    private String imageUrl;

    private ImageResource(long adId, boolean isPrimary, int positionOrder, MultipartFile image) {
        this.adId = adId;
        this.isPrimary = isPrimary;
        this.positionOrder = positionOrder;
        this.image = image;
    }

    public ImageResource(long id, long adId, boolean isPrimary, int positionOrder, String imageUrl) {
        this.id = id;
        this.adId = adId;
        this.isPrimary = isPrimary;
        this.positionOrder = positionOrder;
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

    public int getPositionOrder() {
        return positionOrder;
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
        return id == that.id &&
                adId == that.adId &&
                isPrimary == that.isPrimary &&
                positionOrder == that.positionOrder &&
                image.equals(that.image) &&
                imageUrl.equals(that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adId, isPrimary, positionOrder, image, imageUrl);
    }
}
