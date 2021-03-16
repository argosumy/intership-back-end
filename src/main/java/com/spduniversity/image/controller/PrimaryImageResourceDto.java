package com.spduniversity.image.controller;

import java.util.Objects;
import java.util.StringJoiner;

public class PrimaryImageResourceDto {

    private final long id;
    private final long adId;
    private final String imageUrl;

    private PrimaryImageResourceDto(long id, long adId, String imageUrl) {
        this.id = id;
        this.adId = adId;
        this.imageUrl = imageUrl;
    }

    public static PrimaryImageResourceDto of(long id, long adId, String imageUrl) {
        return new PrimaryImageResourceDto(id, adId, imageUrl);
    }

    public long getId() {
        return id;
    }

    public long getAdId() {
        return adId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimaryImageResourceDto that = (PrimaryImageResourceDto) o;
        return id == that.id &&
                adId == that.adId &&
                imageUrl.equals(that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adId, imageUrl);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PrimaryImageResourceDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("adId=" + adId)
                .add("imageUrl='" + imageUrl + "'")
                .toString();
    }
}
